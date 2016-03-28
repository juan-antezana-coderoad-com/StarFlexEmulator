package com.tierconnect.riot.bridges.emulators.datagen.starflexgen.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.tierconnect.riot.bridges.emulators.datagen.starflexgen.model.RequestMethod;

import java.io.IOException;

/**
 * Created by jantezana on 3/24/16.
 */
public class RequestMethodDeserialize extends JsonDeserializer<RequestMethod> {
    @Override
    public RequestMethod deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        final String jsonValue = p.getText();

        return RequestMethod.fromString(jsonValue).get();
    }
}
