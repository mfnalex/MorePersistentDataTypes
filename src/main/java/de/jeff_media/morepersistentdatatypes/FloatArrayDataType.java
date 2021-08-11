package de.jeff_media.morepersistentdatatypes;

import lombok.SneakyThrows;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class FloatArrayDataType implements PersistentDataType<byte[], float[]> {

    private Charset charset;

    @Override
    public Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @Override
    public Class<float[]> getComplexType() {
        return float[].class;
    }

    @Override
    @SneakyThrows
    public byte[] toPrimitive(float[] floats, PersistentDataAdapterContext itemTagAdapterContext) {
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
    public float[] fromPrimitive(byte[] bytes, PersistentDataAdapterContext itemTagAdapterContext) {
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