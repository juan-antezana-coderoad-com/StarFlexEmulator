package com.tierconnect.riot.bridges.emulators.datagen.starflexgen.factory;


import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.tierconnect.riot.bridges.emulators.datagen.starflexgen.model.*;

/**
 * Created by jantezana on 3/21/16.
 */
public class StarFlexFactory {

    /**
     * Create a starflex by type.
     *
     * @param starFlexType the starflex type
     * @param macId        the value of mac ID
     * @return a starflex
     */
    public Optional<StarFlex> createStarFlex(final StarFlexType starFlexType, final String macId) {
        Preconditions.checkNotNull(starFlexType);
        StarFlex result;

        switch (starFlexType) {
            case STAR_FLEX_DATA: {
                result = generateStarFlexData(macId);
                break;
            }
            case STAR_FLEX_IE: {
                result = generateStarFlexIE(macId);
                break;
            }
            case STAR_FLEX_RESPONSE: {
                result = generateStarFlexResponse(macId);
                break;
            }
            case STAR_FLEX_REQUEST: {
                result = generateStarFlexRequest(macId);
                break;
            }
            default: {
                result = null;
                break;
            }
        }

        return Optional.fromNullable(result);
    }


    private StarFlex generateStarFlexData(final String macId) {
        return generateStarFlexData(macId, StarFlexData.StarFlexDataBuilder.DEFAULT_MAX_TAG_READ_DATA_MESSAGE, StarFlexData.StarFlexDataBuilder.DEFAULT_TAG_READ_DATA_MESSAGE_NUMBER, StarFlexData.StarFlexDataBuilder.DEFAULT_PREFIX_DATA);
    }

    /**
     * Generate a starflex data.
     *
     * @param macId the value of mac ID
     * @return a starflex
     */
    public StarFlex generateStarFlexData(final String macId, final int maxTagReadDataMessage, final int tagReadDataMessageNumber, final String prefixTagReadDataMessage) {
        final StarFlex starFlex = new StarFlexData.StarFlexDataBuilder().setMacId(macId).setRandomValues(maxTagReadDataMessage, tagReadDataMessageNumber, prefixTagReadDataMessage).build();
        return starFlex;
    }

    /**
     * Generate a starflex Interesting Events
     *
     * @param macId the value of mac ID
     * @return a starflex
     */
    private StarFlex generateStarFlexIE(final String macId) {
        final StarFlex starFlex = new StarFlexIE.StarFlexIEBuilder().setMacId(macId).setRandomValues().build();
        return starFlex;
    }

    /**
     * Generate a starflex response.
     *
     * @param macId the value of mac ID
     * @return a starflex
     */
    private StarFlex generateStarFlexResponse(final String macId) {
        final StarFlex starFlex = new StarFlexResponse.StarFlexResponseBuilder().setMacId(macId).setRandomValues().build();
        return starFlex;
    }

    /**
     * Generate a starflex request.
     *
     * @param macId the value of mac ID
     * @return a starflex
     */
    private StarFlex generateStarFlexRequest(final String macId) {
        final StarFlex starFlex = new StarFlexRequest.StarFlexRequestBuilder().setMacId(macId).setRandomValues().build();
        return starFlex;
    }
}
