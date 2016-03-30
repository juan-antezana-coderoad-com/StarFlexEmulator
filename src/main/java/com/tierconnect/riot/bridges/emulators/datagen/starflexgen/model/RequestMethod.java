package com.tierconnect.riot.bridges.emulators.datagen.starflexgen.model;

import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

import java.io.Serializable;

/**
 * Created by jantezana on 3/24/16.
 */
public enum RequestMethod implements Serializable {
    GET("GET"),
    HEAD("HEAD"),
    POST("POST"),
    PUT("PUT"),
    PATCH("PATCH"),
    DELETE("DELETE"),
    OPTIONS("OPTIONS"),
    TRACE("TRACE");

    private String value;

    /**
     * Builds an instance of RequestMethod
     *
     * @param value the value
     */
    RequestMethod(final String value) {
        Preconditions.checkNotNull(value);
        this.value = value;

    }

    /**
     * Gets the value.
     *
     * @return the value
     */
    @JsonValue
    public String getValue() {
        return value;
    }

    /**
     * Gets a RequestMethod from a String
     *
     * @param value the value of string
     * @return the RequestMethod
     */
    public static Optional<RequestMethod> fromString(final String value) {
        RequestMethod result = null;
        for (RequestMethod method :
                RequestMethod.values()) {
            if (method.getValue().equalsIgnoreCase(value)) {
                result = method;
                break;
            }
        }

        return Optional.fromNullable(result);
    }
}
