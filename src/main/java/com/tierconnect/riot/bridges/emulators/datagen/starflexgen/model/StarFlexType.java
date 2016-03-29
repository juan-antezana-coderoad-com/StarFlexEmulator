package com.tierconnect.riot.bridges.emulators.datagen.starflexgen.model;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

/**
 * Created by jantezana on 3/21/16.
 */
public enum StarFlexType {
    STAR_FLEX_DATA("data"),
    STAR_FLEX_IE("ie"),
    STAR_FLEX_REQUEST("request"),
    STAR_FLEX_RESPONSE("response");


    private String value;

    /**
     * Builds an instance of StarFlexType.
     *
     * @param value the value
     */
    StarFlexType(final String value) {
        Preconditions.checkNotNull(value);
        this.value = value;
    }

    /**
     * Gets the value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * Gets a StarFlexType from a string
     *
     * @param value the string value
     * @return the StarFlexType
     */
    public static Optional<StarFlexType> fromString(final String value) {
        Preconditions.checkNotNull(value);
        StarFlexType result = null;

        for (StarFlexType starFlexType :
                StarFlexType.values()) {
            if (starFlexType.getValue().equalsIgnoreCase(value)) {
                result = starFlexType;
                break;
            }
        }

        return Optional.fromNullable(result);
    }
}
