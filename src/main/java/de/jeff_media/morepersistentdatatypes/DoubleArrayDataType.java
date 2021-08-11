package de.jeff_media.morepersistentdatatypes;

import lombok.SneakyThrows;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class DoubleArrayDataType implements PersistentDataType<byte[], double[]> {

    private Charset charset;

    @Override
    public Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @Override
    public Class<double[]> getComplexType() {
        return double[].class;
    }

    @Override
    @SneakyThrows
    public byte[] toPrimitive(double[] doubles, PersistentDataAdapterContext itemTagAdapterContext) {
        try(
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(bos)
        ) {
            dos.writeInt(doubles.length);
            for(double number : doubles) {
                dos.writeDouble(number);
            }
            dos.flush();
            return bos.toByteArray();
        }
    }

    @Override
    @SneakyThrows
    public double[] fromPrimitive(byte[] bytes, PersistentDataAdapterContext itemTagAdapterContext) {
        try (
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            DataInputStream dis = new DataInputStream(bis)
        ) {
            double[] doubles = new double[dis.readInt()];
            for(int i = 0; i < doubles.length; i++) {
                doubles[i] = dis.readDouble();
            }
            return doubles;
        }
    }
}