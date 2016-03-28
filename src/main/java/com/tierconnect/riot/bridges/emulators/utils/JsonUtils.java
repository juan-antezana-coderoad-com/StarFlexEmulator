package com.tierconnect.riot.bridges.emulators.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import com.google.common.base.Preconditions;

import com.tierconnect.riot.bridges.emulators.datagen.starflexgen.model.StarFlexRequest;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by jantezana on 3/21/16.
 */
public final class JsonUtils {
    static Logger logger = Logger.getLogger(JsonUtils.class);
    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String convertObjectToJson(final Object object) {
        Preconditions.checkNotNull(object);
        String result = null;
        try {
            OBJECT_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            result = OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
        }

        return result;
    }

    public static StarFlexRequest.Request convertStringToRequest(final String value) throws IOException {
        Preconditions.checkNotNull(value);
        StarFlexRequest.Request result = OBJECT_MAPPER.readValue(value, StarFlexRequest.Request.class);

        return result;
    }
}

