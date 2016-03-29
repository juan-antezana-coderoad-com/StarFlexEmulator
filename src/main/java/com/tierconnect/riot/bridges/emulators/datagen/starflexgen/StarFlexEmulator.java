package com.tierconnect.riot.bridges.emulators.datagen.starflexgen;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

import com.tierconnect.riot.bridges.emulators.datagen.starflexgen.factory.StarFlexFactory;
import com.tierconnect.riot.bridges.emulators.datagen.starflexgen.model.StarFlex;
import com.tierconnect.riot.bridges.emulators.datagen.starflexgen.model.StarFlexRequest;
import com.tierconnect.riot.bridges.emulators.datagen.starflexgen.model.StarFlexResponse;
import com.tierconnect.riot.bridges.emulators.datagen.starflexgen.model.StarFlexType;

import com.tierconnect.riot.bridges.emulators.utils.JsonUtils;
import com.tierconnect.riot.bridges.emulators.utils.StringUtils;
import org.apache.commons.cli.*;
import org.apache.log4j.Logger;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by jantezana on 3/21/16.
 */
public class StarFlexEmulator {

    private static final Logger LOGGER = Logger.getLogger(StarFlexEmulator.class);
    private static final StarFlexFactory STAR_FLEX_FACTORY = new StarFlexFactory();
    private static final CommandLineParser PARSER = new BasicParser();
    public static final String DEFAULT_HOST = "localhost";
    public static final int DEFAULT_PORT = 1883;
    public static final String DEFAULT_MAC_ID = "F48E729BB";
    public static final int DEFAULT_RECORDS_NUMBER = -1;
    public static final int DEFAULT_NUMBER_OF_SECONDS = 1;
    private CommandLine line;
    private static final Options OPTIONS = new Options();

    private String mqttHost;
    private int mqttPort;
    private StarFlexType starFlexType;
    private String macId;
    private int recordsNumber;
    private int seconds;


    public static final String DEFAULT_STAR_FLEX_TYPE = "ie";

    static {
        // Adds the options.
        OPTIONS.addOption("h", true, String.format("mqtt host (defaults to %s)", DEFAULT_HOST));
        OPTIONS.addOption("p", true, String.format("mqtt port (defaults to %d)", DEFAULT_PORT));
        OPTIONS.addOption("t", true, String.format("starflex type (ex. data, %s, request, response)", DEFAULT_STAR_FLEX_TYPE));
        OPTIONS.addOption("m", true, String.format("macId (defaults to %s)", DEFAULT_MAC_ID));
        OPTIONS.addOption("r", true, String.format("number of records (defaults to %d (Unlimited))", DEFAULT_RECORDS_NUMBER));
        OPTIONS.addOption("s", true, String.format("number of seconds to run (defaults to %d)", DEFAULT_NUMBER_OF_SECONDS));

        OPTIONS.addOption("help", false, "show this help");
    }

    /**
     * Build an instance of StarFlexEmulator.
     */
    public StarFlexEmulator() {
        mqttHost = DEFAULT_HOST;
        mqttPort = DEFAULT_PORT;
        starFlexType = StarFlexType.STAR_FLEX_IE;
        macId = DEFAULT_MAC_ID;
        recordsNumber = DEFAULT_RECORDS_NUMBER;
        seconds = DEFAULT_NUMBER_OF_SECONDS;
    }

    /**
     * Parsesthe options
     *
     * @param args the arguments
     */
    public void parseOptions(String[] args) {
        synchronized (this) {
            try {
                line = PARSER.parse(OPTIONS, args);
            } catch (ParseException exp) {
                System.err.println(String.format("Parsing failed.  Reason: %s", exp.getMessage()));
                final HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("java -cp xxxx " + StarFlexEmulator.class.getName(), OPTIONS);
                System.exit(0);
            }

            if (line.hasOption("help")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp(String.format("java -cp %s", this.getClass().getSimpleName()), OPTIONS);
                System.exit(0);
            }

            mqttHost = line.hasOption("h") ? line.getOptionValue("h") : DEFAULT_HOST;
            mqttPort = line.hasOption("p") ? Integer.parseInt(line.getOptionValue("p")) : DEFAULT_PORT;

            if (line.hasOption("t")) {
                Optional<StarFlexType> starFlexTypeOptional = StarFlexType.fromString(line.getOptionValue("t"));
                if (starFlexTypeOptional.isPresent()) {
                    starFlexType = starFlexTypeOptional.get();
                } else {
                    System.err.println(String.format("the starflex type '%s' is invalid.", line.getOptionValue("t")));
                    System.exit(1);
                }

            } else {
                starFlexType = StarFlexType.STAR_FLEX_IE;
            }

            macId = line.hasOption("m") ? line.getOptionValue("m") : DEFAULT_MAC_ID;

            recordsNumber = line.hasOption("r") ? Integer.parseInt(line.getOptionValue("r")) : DEFAULT_RECORDS_NUMBER;
            seconds = line.hasOption("s") ? Integer.parseInt(line.getOptionValue("s")) : DEFAULT_NUMBER_OF_SECONDS;
        }
    }


    /**
     * Gets the value of mqttHost
     *
     * @return the value of mqttHost
     */
    public String getMqttHost() {
        return mqttHost;
    }

    /**
     * Gets the value of mqttPort
     *
     * @return the value of mqttPort
     */
    public int getMqttPort() {
        return mqttPort;
    }

    /**
     * Gets the value of macId
     *
     * @return the value of macId
     */
    public String getMacId() {
        return macId;
    }

    /**
     * Gets the value of recordsNumber
     *
     * @return the value of recordsNumber
     */
    public int getRecordsNumber() {
        return recordsNumber;
    }

    /**
     * Gets the value of seconds
     *
     * @return the value of seconds
     */
    public int getSeconds() {
        return seconds;
    }

    /**
     * Stars to emulate the data.
     *
     * @throws MqttException
     * @throws InterruptedException
     */
    public void emulateData() throws MqttException, InterruptedException {
        synchronized (this) {
            final String serverURI = String.format("tcp://%s:%d", mqttHost, mqttPort);
            final String uniqueID = StringUtils.shortUUID();

            switch (starFlexType) {
                case STAR_FLEX_IE:
                case STAR_FLEX_REQUEST:
                case STAR_FLEX_RESPONSE: {
                    final MqttAsyncStarFlexClient starFlexClient = new MqttAsyncStarFlexClient(serverURI, uniqueID, macId, new MemoryPersistence());
                    LOGGER.info(String.format("Generated the Clien ID: %s_%s", macId, uniqueID));

                    if (!starFlexClient.isConnected()) starFlexClient.connect().waitForCompletion();

                    StarFlex starFlex;

                    if (isUnlimitedRecords()) {
                        while (true) {
                            starFlex = generateStarFlexByType(starFlexType);
                            this.publish(starFlexClient, starFlex);
                        }
                    } else {
                        if (recordsNumber > 0) {
                            for (int index = 0; index < recordsNumber; index++) {
                                starFlex = generateStarFlexByType(starFlexType);
                                // Star the emulation by type.
                                this.publish(starFlexClient, starFlex);
                            }

                            LOGGER.info(String.format("The emulation for the Mac ID: %s was finished successfully.", this.getMacId()));
                            System.exit(0);
                        } else {
                            System.err.println(String.format("Invalid number of records : %d", recordsNumber));
                            System.exit(0);
                        }
                    }

                    break;
                }
                case STAR_FLEX_DATA: {
                    final MqttAsyncStarFlexClient starFlexClient = new MqttAsyncStarFlexClient(serverURI, uniqueID, macId, new MemoryPersistence());
                    LOGGER.info(String.format("Generated the Clien ID: %s_%s", macId, uniqueID));
                    if (!starFlexClient.isConnected()) starFlexClient.connect().waitForCompletion();

                    // Getting the response topic filter.
                    StarFlexResponse response = new StarFlexResponse.StarFlexResponseBuilder().setDefaultValues().setMacId(macId).build();
                    publish(starFlexClient, response);

                    StarFlex starFlex;
                    if (isUnlimitedRecords()) {
                        while (true) {
                            starFlex = generateStarFlexByType(starFlexType);
                            this.publish(starFlexClient, starFlex);
                        }
                    } else {
                        if (recordsNumber > 0) {
                            for (int index = 0; index < recordsNumber; index++) {
                                starFlex = generateStarFlexByType(starFlexType);
                                this.publish(starFlexClient, starFlex);
                            }

                            LOGGER.info(String.format("The emulation for the Mac ID: %s was finished successfully.", this.getMacId()));
                            System.exit(0);
                        } else {
                            System.err.println(String.format("Invalid number of records : %d", recordsNumber));
                            System.exit(0);
                        }
                    }
                    break;
                }

                default:
                    break;
            }
        }
    }

    /**
     * Verify if recordsNumber is unlimited.
     *
     * @return true if is unlimited
     */
    private boolean isUnlimitedRecords() {
        return recordsNumber == -1;
    }

    /**
     * Generate the starflex by type.
     *
     * @param starFlexType the starFlexType
     * @return a starflex
     */
    private StarFlex generateStarFlexByType(final StarFlexType starFlexType) {
        Preconditions.checkNotNull(starFlexType);
        StarFlex starFlex;

        // Generate a start flex by type.
        starFlex = STAR_FLEX_FACTORY.createStarFlex(starFlexType, macId).get();
        return starFlex;
    }

    /**
     * Publish a starflex.
     *
     * @param starFlexClient the starflex client
     * @param starFlex       the starflex
     * @throws InterruptedException
     * @throws MqttException
     */
    private void publish(final MqttAsyncStarFlexClient starFlexClient, final StarFlex starFlex) throws InterruptedException, MqttException {
        starFlexClient.publish(starFlex);

        // Apply the delay.
        Thread.sleep(seconds * 1000);
    }

    /**
     * Subscribe a topic filter.
     *
     * @param starFlexClient the starflex client
     * @param topicFilter    the topix filter
     * @throws InterruptedException
     * @throws MqttException
     */
    private void subscribe(final MqttAsyncStarFlexClient starFlexClient, final String topicFilter) throws InterruptedException, MqttException {
        starFlexClient.subscribe(topicFilter);

        // Apply the delay.
        Thread.sleep(seconds * 1000);
    }

    /**
     * Main method.
     *
     * @param args the arguments
     */
    public static void main(final String args[]) {
        Preconditions.checkNotNull(args);
        StarFlexEmulator starFlexEmulator = new StarFlexEmulator();
        starFlexEmulator.parseOptions(args);

        try {
            starFlexEmulator.emulateData();

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }
}
