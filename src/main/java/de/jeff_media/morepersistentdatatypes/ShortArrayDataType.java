package de.jeff_media.morepersistentdatatypes;

import lombok.SneakyThrows;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ShortArrayDataType implements PersistentDataType<byte[], short[]> {

    private Charset charset;

    @Override
    public Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @Override
    public Class<short[]> getComplexType() {
        return short[].class;
    }

    @Override
    @SneakyThrows
    public byte[] toPrimitive(short[] shorts, PersistentDataAdapterContext itemTagAdapterContext) {
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
    public short[] fromPrimitive(byte[] bytes, PersistentDataAdapterContext itemTagAdapterContext) {
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