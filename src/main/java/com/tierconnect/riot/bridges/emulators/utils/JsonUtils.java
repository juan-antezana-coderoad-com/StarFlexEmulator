package com.tierconnect.riot.bridges.emulators.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import com.google.common.base.Preconditions;

import com.tierconnect.riot.bridges.emulators.datagen.starflexgen.model.StarFlexRequest;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Created by jantezana on 3/21/16.
 */
public final class JsonUtils {
    static Logger logger = Logger.getLogger(JsonUtils.class);
    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * Converts object to json string.
     *
     * @param object the object
     * @return a json string
     */
    public static String convertObjectToJson(final Object object) {
        synchronized (JsonUtils.class) {
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
    }

    /**
     * Converts a String a Request
     *
     * @param value the value of string
     * @return a Request
     * @throws IOException
     */
    public static StarFlexRequest.Request convertStringToRequest(final String value) throws IOException {
        synchronized (JsonUtils.class) {
            Preconditions.checkNotNull(value);
            StarFlexRequest.Request result = OBJECT_MAPPER.readValue(value, StarFlexRequest.Request.class);

            return result;
        }
    }
}

