package com.tierconnect.riot.bridges.emulators.datagen.starflexgen;

import com.google.common.base.Preconditions;
import com.tierconnect.riot.bridges.emulators.datagen.starflexgen.model.StarFlex;
import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Created by jantezana on 3/24/16.
 */
public class MqttAsyncStarFlexClient extends MqttAsyncClient {
    private static final Logger LOGGER = Logger.getLogger(MqttAsyncStarFlexClient.class);
    public static final int DEFAULT_QOS = 1;

    private String topic;
    private MqttMessage message;

    public MqttAsyncStarFlexClient(String serverURI, String clientId) throws MqttException {
        super(serverURI, clientId);
    }

    private void verifyConnection() throws InterruptedException {
        while (!this.isConnected()) {
            Thread.sleep(100);
            LOGGER.warn(String.format("Client Id : %s is trying to connect......", this.getClientId()));
        }

        LOGGER.info(String.format("client Id: %s connected : %b", this.getClientId(), this.isConnected()));
    }

    public void publish(final StarFlex starFlex) throws MqttException, InterruptedException {
        Preconditions.checkNotNull(starFlex);
        this.topic = starFlex.getTopic();
        this.message = new MqttMessage(starFlex.getMessage().getBytes());
        this.verifyConnection();
        this.publish(topic, message);
        LOGGER.info(String.format("Published the topic:%s message:%s", starFlex.getTopic(), starFlex.getMessage()));
    }

    public void subscribe(final String topicFilter) throws MqttException, InterruptedException {
        Preconditions.checkNotNull(topicFilter);
        this.verifyConnection();
        this.subscribe(topicFilter, DEFAULT_QOS);
        LOGGER.info(String.format("Subscribed to the filter topic:%s", topicFilter));
    }
}
