package com.jeff_media.morepersistentdatatypes.implementation;

import lombok.SneakyThrows;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.charset.Charset;

public class ShortArrayDataType implements PersistentDataType<byte[], short[]> {

    private Charset charset;

    @Override
    public @NotNull Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @Override
    public @NotNull Class<short[]> getComplexType() {
        return short[].class;
    }

    @Override
    @SneakyThrows
    public byte @NotNull [] toPrimitive(short[] shorts, @NotNull PersistentDataAdapterContext itemTagAdapterContext) {
        try(
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                DataOutputStream dos = new DataOutputStream(bos)
        ) {
            dos.writeInt(shorts.length);
            for(short number : shorts) {
                dos.writeShort(number);
            }
            dos.flush();
            return bos.toByteArray();
        }
    }

    @Override
    @SneakyThrows
    public short @NotNull [] fromPrimitive(byte @NotNull [] bytes, @NotNull PersistentDataAdapterContext itemTagAdapterContext) {
        try (
                ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
                DataInputStream dis = new DataInputStream(bis)
        ) {
            short[] shorts = new short[dis.readInt()];
            for(int i = 0; i < shorts.length; i++) {
                shorts[i] = dis.readShort();
            }
            return shorts;
        }
    }
}