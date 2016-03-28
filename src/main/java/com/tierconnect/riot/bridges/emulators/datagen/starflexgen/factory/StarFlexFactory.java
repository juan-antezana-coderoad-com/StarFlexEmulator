package com.tierconnect.riot.bridges.emulators.datagen.starflexgen.factory;


import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

import com.tierconnect.riot.bridges.emulators.datagen.starflexgen.model.*;

/**
 * Created by jantezana on 3/21/16.
 */
public class StarFlexFactory {
    public Optional<StarFlex> getStarFlex(final StarFlexType starFlexType) {
        Preconditions.checkNotNull(starFlexType);
        StarFlex result;

        switch (starFlexType) {
            case STAR_FLEX_DATA: {
                result = generateStarFlexData(StarFlex.DEFAULT_MAC_ID);
                break;
            }
            case STAR_FLEX_IE: {
                result = generateStarFlexIE(StarFlex.DEFAULT_MAC_ID);
                break;
            }
            case STAR_FLEX_RESPONSE: {
                result = generateStarFlexResponse(StarFlex.DEFAULT_MAC_ID);
                break;
            }
            default: {
                result = null;
                break;
            }
        }

        return Optional.fromNullable(result);
    }

    public Optional<StarFlex> getStarFlex(final StarFlexType starFlexType, final String macId) {
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
        final StarFlex starFlex = new StarFlexData.StarFlexDataBuilder().setMacId(macId).setRandomValues().build();
        return starFlex;
    }

    private StarFlex generateStarFlexIE(final String macId) {
        final StarFlex starFlex = new StarFlexIE.StarFlexIEBuilder().setMacId(macId).setRandomValues().build();
        return starFlex;
    }

    private StarFlex generateStarFlexResponse(final String macId) {
        final StarFlex starFlex = new StarFlexResponse.StarFlexResponseBuilder().setMacId(macId).setRandomValues().build();
        return starFlex;
    }

    private StarFlex generateStarFlexRequest(final String macId) {
        final StarFlex starFlex = new StarFlexRequest.StarFlexRequestBuilder().setMacId(macId).setRandomValues().build();
        return starFlex;
    }

}
