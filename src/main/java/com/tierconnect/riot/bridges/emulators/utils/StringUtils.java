package com.tierconnect.riot.bridges.emulators.utils;

import java.nio.ByteBuffer;
import java.util.UUID;

/**
 * Created by jantezana on 3/24/16.
 */
public final class StringUtils {

    /**
     * Generates a short UUID.
     *
     * @return a short UUID
     */
    public static final String shortUUID() {
        synchronized (StringUtils.class) {
            final UUID uuid = UUID.randomUUID();
            final long l = ByteBuffer.wrap(uuid.toString().getBytes()).getLong();

            return Long.toString(l, Character.MAX_RADIX);
        }
    }
}
