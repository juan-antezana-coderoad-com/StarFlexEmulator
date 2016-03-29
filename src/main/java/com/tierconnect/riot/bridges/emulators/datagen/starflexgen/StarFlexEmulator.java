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


    public static final String DEFAULT_STAR_FLEX_TYPE = "IE";

    static {
        // Adds the options.
        OPTIONS.addOption("h", true, String.format("mqtt host (defaults to %s)", DEFAULT_HOST));
        OPTIONS.addOption("p", true, String.format("mqtt port (defaults to %d)", DEFAULT_PORT));
        OPTIONS.addOption("t", true, String.format("starflex type (ex. data, %s)", DEFAULT_STAR_FLEX_TYPE));
        OPTIONS.addOption("m", true, String.format("macId (defaults to %s)", DEFAULT_MAC_ID));
        OPTIONS.addOption("r", true, String.format("number of records (defaults to %d (Unlimited))", DEFAULT_RECORDS_NUMBER));
        OPTIONS.addOption("s", true, String.format("number of seconds to run (defaults to %d)", DEFAULT_NUMBER_OF_SECONDS));

        OPTIONS.addOption("help", false, "show this help");
    }

    public StarFlexEmulator() {
        mqttHost = DEFAULT_HOST;
        mqttPort = DEFAULT_PORT;
        starFlexType = StarFlexType.STAR_FLEX_IE;
        macId = DEFAULT_MAC_ID;
        recordsNumber = DEFAULT_RECORDS_NUMBER;
        seconds = DEFAULT_NUMBER_OF_SECONDS;
    }

    public void parseOptions(String[] args) {
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


    public String getMqttHost() {
        return mqttHost;
    }

    public int getMqttPort() {
        return mqttPort;
    }

    public String getMacId() {
        return macId;
    }

    public int getRecordsNumber() {
        return recordsNumber;
    }

    public int getSeconds() {
        return seconds;
    }

    public void emulateData() throws MqttException, InterruptedException {
        final String serverURI = String.format("tcp://%s:%d", mqttHost, mqttPort);
        final String uniqueID = MqttAsyncStarFlexClient.generateClientId();
        final MqttAsyncStarFlexClient starFlexClient = new MqttAsyncStarFlexClient(serverURI, uniqueID, new MemoryPersistence());
        LOGGER.info(String.format("Generated the Clien ID: %s for the Mac ID: %s", uniqueID, macId));
        starFlexClient.connect();
        StarFlex starFlex;

        switch (starFlexType) {
            case STAR_FLEX_IE:
            case STAR_FLEX_REQUEST:
            case STAR_FLEX_RESPONSE: {
                starFlexClient.setCallback(new MqttCallback() {
                    @Override
                    public void connectionLost(Throwable cause) {
                        LOGGER.warn(String.format("Connection Lost: %s ", cause.getMessage()));
                    }

                    @Override
                    public void messageArrived(String topic, MqttMessage message) throws Exception {

                    }

                    @Override
                    public void deliveryComplete(IMqttDeliveryToken token) {

                    }
                });
                if (isUnlimitedRecords()) {
                    while (true) {
                        starFlex = generateStarFlexByType(starFlexType);
                        // Star the emulation by type.
                        this.start(starFlexType, starFlexClient, starFlex);
                    }
                } else {
                    if (recordsNumber > 0) {
                        for (int index = 0; index < recordsNumber; index++) {
                            starFlex = generateStarFlexByType(starFlexType);
                            // Star the emulation by type.
                            this.start(starFlexType, starFlexClient, starFlex);
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
                // Getting the resquest topic filter.
                final StarFlexRequest request = new StarFlexRequest.StarFlexRequestBuilder().setDefaultValues().setMacId(macId).build();

                // Subscribe to a request topic filter by mac ID.
                subscribe(starFlexClient, request.getTopic());

                // Prints the message arrived
                starFlexClient.setCallback(new MqttCallback() {
                    @Override
                    public void connectionLost(Throwable cause) {
                        LOGGER.warn(String.format("Connection Lost: %s ", cause.getMessage()));
                    }

                    @Override
                    public void messageArrived(String topic, MqttMessage message) throws Exception {
                        LOGGER.info(String.format("Got Topic: %s  Message: %s", topic, message));
                        StarFlexRequest.Request request = JsonUtils.convertStringToRequest(message.toString());
                        LOGGER.info(String.format("Got the command: %s", request.getCmd()));
                        StarFlexRequest.Command command = StarFlexRequest.Command.fromString(request.getCmd()).get();

                        final String otherUniqueID = MqttAsyncStarFlexClient.generateClientId();
                        MqttAsyncStarFlexClient client = new MqttAsyncStarFlexClient(serverURI, otherUniqueID);
                        client.connect();
                        switch (command) {
                            case RFID_SUBSCRIPTIONS: {
                                StarFlexResponse response = new StarFlexResponse.StarFlexResponseBuilder().setMacId(macId).setDefaultValues().build();
                                client.publish(response);
                                break;
                            }
                            case RFID_EVENTS_DEFAULT_VIZIX_SUBSCRIPTIONS: {
                                StarFlexResponse response = new StarFlexResponse.StarFlexResponseBuilder().setMacId(macId).setDefaultValues().build();
                                response.getResponse().setUuid("38e953bd-370d-4d05-b652-d5fd430f9425");
                                List<String> body = new LinkedList<String>();
                                body.add("OK");
                                response.getResponse().setBody(body);
                                client.publish(response);

                                break;
                            }
                            default: {
                                LOGGER.warn(String.format("Invalid command: %s", request.getCmd()));
                                break;
                            }
                        }
                    }

                    @Override
                    public void deliveryComplete(IMqttDeliveryToken token) {
                    }
                });

                break;
            }
        }
    }

    private boolean isUnlimitedRecords() {
        return recordsNumber == -1;
    }


    private void start(StarFlexType starFlexType, MqttAsyncStarFlexClient starFlexClient, StarFlex starFlex) throws MqttException, InterruptedException {
        switch (starFlexType) {
            case STAR_FLEX_IE:
            case STAR_FLEX_REQUEST:
            case STAR_FLEX_RESPONSE: {
                publish(starFlexClient, starFlex);
                break;
            }

            default: {
                break;
            }
        }
    }

    private StarFlex generateStarFlexByType(StarFlexType starFlexType) {
        StarFlex starFlex;

        // Generate a start flex by type.
        starFlex = STAR_FLEX_FACTORY.getStarFlex(starFlexType, macId).get();
        return starFlex;
    }

    private void publish(final MqttAsyncStarFlexClient starFlexClient, final StarFlex starFlex) throws InterruptedException, MqttException {
        starFlexClient.publish(starFlex);

        // Apply the delay.
        Thread.sleep(seconds * 1000);
    }

    private void subscribe(final MqttAsyncStarFlexClient starFlexClient, final String topicFilter) throws InterruptedException, MqttException {
        starFlexClient.subscribe(topicFilter);

        // Apply the delay.
        Thread.sleep(seconds * 1000);
    }

    public static void main(String args[]) {
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
