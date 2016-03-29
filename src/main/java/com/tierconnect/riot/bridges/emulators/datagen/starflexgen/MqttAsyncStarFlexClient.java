package com.tierconnect.riot.bridges.emulators.datagen.starflexgen;

import com.google.common.base.Preconditions;

import com.tierconnect.riot.bridges.emulators.datagen.starflexgen.model.StarFlex;

import org.apache.log4j.Logger;

import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Created by jantezana on 3/24/16.
 */
public class MqttAsyncStarFlexClient extends MqttAsyncClient {
    private static final Logger LOGGER = Logger.getLogger(MqttAsyncStarFlexClient.class);
    public static final int DEFAULT_QOS = 0;

    private String macId;
    private String topic;
    private MqttMessage message;

    /**
     * Builds a instance of MqttAsyncStarFlexClient.
     *
     * @param serverURI   the value of server URI
     * @param clientId    the value of client ID
     * @param macId       the value of mac ID
     * @param persistence the persistence
     * @throws MqttException
     */
    public MqttAsyncStarFlexClient(final String serverURI, final String clientId, final String macId, final MqttClientPersistence persistence) throws MqttException {
        super(serverURI, clientId, persistence);
        this.macId = macId;
    }

    /**
     * Sets the value of macID,
     *
     * @param macId the new value of mac ID
     */
    public void setMacId(final String macId) {
        Preconditions.checkNotNull(macId);
        this.macId = macId;
    }

    /**
     * Check the connection.
     *
     * @throws InterruptedException
     * @throws MqttException
     */
    public void checkConnection() throws InterruptedException, MqttException {
        while (!this.isConnected()) {
            LOGGER.warn(String.format("Client Id : %s_%s is trying to connect......", this.macId, this.getClientId()));
            this.connect();
            Thread.sleep(3000);
        }
        LOGGER.info(String.format("client Id: %s_%s is connected", this.macId, this.getClientId()));
    }

    /**
     * Publish a starflex.
     *
     * @param starFlex the starflex
     * @throws MqttException
     * @throws InterruptedException
     */
    public void publish(final StarFlex starFlex) throws MqttException, InterruptedException {
        Preconditions.checkNotNull(starFlex);
        this.topic = starFlex.getTopic();
        this.message = new MqttMessage(starFlex.getMessage().getBytes());
        this.message.setQos(DEFAULT_QOS);
        this.message.setRetained(true);

        this.checkConnection();
        this.publish(topic, message);
        LOGGER.info(String.format("Published the topic:%s message:%s", starFlex.getTopic(), starFlex.getMessage()));
    }

    /**
     * Subscribe a topic filter
     *
     * @param topicFilter the topic filter
     * @throws MqttException
     * @throws InterruptedException
     */
    public void subscribe(final String topicFilter) throws MqttException, InterruptedException {
        Preconditions.checkNotNull(topicFilter);
        this.checkConnection();
        this.subscribe(topicFilter, DEFAULT_QOS);
        LOGGER.info(String.format("Subscribed to the filter topic:%s", topicFilter));
    }
}
