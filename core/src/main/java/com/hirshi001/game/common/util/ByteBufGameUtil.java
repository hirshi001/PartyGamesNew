package com.hirshi001.game.common.util;

import io.netty.buffer.ByteBuf;

import java.util.UUID;

public class ByteBufGameUtil {

    /**
     * Writes the UUID to the ByteBuf in 16 bytes (2 longs)
     * @param uuid the UUID to write to the ByteBuf
     * @param buf the ByteBuf to write the UUID to
     */
    public static void writeUUIDToBuf(UUID uuid, ByteBuf buf){
        buf.writeLong(uuid.getMostSignificantBits());
        buf.writeLong(uuid.getLeastSignificantBits());
    }

    /**
     * Reads a UUID from the ByteBuf in 16 bytes (2 longs)
     * @param buf the ByteBuf to read data from
     * @return UUID
     */
    public static UUID getUUID(ByteBuf buf){
        return new UUID(buf.readLong(), buf.readLong());
    }

}
