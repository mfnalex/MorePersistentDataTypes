package com.jeff_media.morepersistentdatatypes.implementation;

import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class BooleanArrayDataType implements PersistentDataType<byte[], boolean[]> {
    @NotNull
    @Override
    public Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @NotNull
    @Override
    public Class<boolean[]> getComplexType() {
        return boolean[].class;
    }

    @Override
    public byte @NotNull [] toPrimitive(final boolean[] booleans, final @NotNull PersistentDataAdapterContext context) {
        final byte[] bytes = new byte[booleans.length];
        for(int i = 0; i < booleans.length; i++) {
            bytes[i] = (byte) (booleans[i] ? 1 : 0);
        }
        return bytes;
    }

    @Override
    public boolean @NotNull [] fromPrimitive(final byte[] bytes, final @NotNull PersistentDataAdapterContext context) {
        final boolean[] booleans = new boolean[bytes.length];
        for(int i = 0; i < bytes.length; i++) {
            booleans[i] = bytes[i] != 0;
        }
        return booleans;
    }
}
