package com.tierconnect.riot.bridges.emulators.datagen.starflexgen.model;

import java.io.Serializable;

/**
 * Created by jantezana on 3/21/16.
 */
public interface StarFlex extends Serializable {

    String DEFAULT_MAC_ID = "F48E729BB";

    String HEADER = "/v1/flex";

    /**
     * Gest the header.
     *
     * @return the header
     */
    String getHeader();

    /**
     * Gets the topic.
     *
     * @return the topic
     */
    String getTopic();

    /**
     * Gets the message
     *
     * @return the message
     */
    String getMessage();
}
