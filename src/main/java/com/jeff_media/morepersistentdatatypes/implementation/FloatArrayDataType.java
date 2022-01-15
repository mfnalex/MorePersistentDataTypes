package com.jeff_media.morepersistentdatatypes.implementation;

import lombok.SneakyThrows;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.charset.Charset;

public class FloatArrayDataType implements PersistentDataType<byte[], float[]> {

    private Charset charset;

    @Override
    public @NotNull Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @Override
    public @NotNull Class<float[]> getComplexType() {
        return float[].class;
    }

    @Override
    @SneakyThrows
    public byte @NotNull [] toPrimitive(float[] floats, @NotNull PersistentDataAdapterContext itemTagAdapterContext) {
        try(
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                DataOutputStream dos = new DataOutputStream(bos)
        ) {
            dos.writeInt(floats.length);
            for(float number : floats) {
                dos.writeFloat(number);
            }
            dos.flush();
            return bos.toByteArray();
        }
    }

    @Override
    @SneakyThrows
    public float @NotNull [] fromPrimitive(byte @NotNull [] bytes, @NotNull PersistentDataAdapterContext itemTagAdapterContext) {
        try (
                ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
                DataInputStream dis = new DataInputStream(bis)
        ) {
            float[] floats = new float[dis.readInt()];
            for(int i = 0; i < floats.length; i++) {
                floats[i] = dis.readFloat();
            }
            return floats;
        }
    }
}