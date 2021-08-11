package de.jeff_media.morepersistentdatatypes.implementation;

import org.bukkit.Bukkit;
import org.bukkit.block.data.BlockData;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class BlockDataDataType implements PersistentDataType<String, BlockData> {
    @NotNull
    @Override
    public Class<String> getPrimitiveType() {
        return String.class;
    }

    @NotNull
    @Override
    public Class<BlockData> getComplexType() {
        return BlockData.class;
    }

    @NotNull
    @Override
    public String toPrimitive(@NotNull BlockData blockData, @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
        return blockData.getAsString();
    }

    @NotNull
    @Override
    public BlockData fromPrimitive(@NotNull String string, @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
        return Bukkit.createBlockData(string);
    }
}
