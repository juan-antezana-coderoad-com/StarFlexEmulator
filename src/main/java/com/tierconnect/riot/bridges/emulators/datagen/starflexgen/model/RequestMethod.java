package com.tierconnect.riot.bridges.emulators.datagen.starflexgen.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.tierconnect.riot.bridges.emulators.datagen.starflexgen.deserialize.RequestMethodDeserialize;

import java.io.Serializable;

/**
 * Created by jantezana on 3/24/16.
 */
@JsonDeserialize(using = RequestMethodDeserialize.class)
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

    RequestMethod(final String value) {
        Preconditions.checkNotNull(value);
        this.value = value;

    }

    @JsonValue
    public String getValue() {
        return value;
    }

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