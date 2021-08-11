package de.jeff_media.morepersistentdatatypes;

import org.bukkit.Bukkit;
import org.bukkit.block.data.BlockData;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.nio.charset.StandardCharsets;

class BlockDataDataType implements PersistentDataType<byte[], BlockData> {
    @NotNull
    @Override
    public Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @NotNull
    @Override
    public Class<BlockData> getComplexType() {
        return BlockData.class;
    }

    @NotNull
    @Override
    public byte[] toPrimitive(@NotNull BlockData blockData, @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
        return blockData.getAsString().getBytes(StandardCharsets.UTF_8);
    }

    @NotNull
    @Override
    public BlockData fromPrimitive(@NotNull byte[] bytes, @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
        return Bukkit.createBlockData(new String(bytes,StandardCharsets.UTF_8));
    }
}
