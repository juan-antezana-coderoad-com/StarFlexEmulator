package com.tierconnect.riot.bridges.emulators.datagen.starflexgen.model;

import java.io.Serializable;

/**
 * Created by jantezana on 3/21/16.
 */
public interface StarFlex extends Serializable{

    String DEFAULT_MAC_ID = "F48E729BB";

    String HEADER = "/v1/flex";

    String getHeader();

    String getTopic();

    String getMessage();
}
