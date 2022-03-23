/*
 * Copyright (c) 2022 Alexander Majka (mfnalex) / JEFF Media GbR
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of  MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.jeff_media.morepersistentdatatypes.datatypes;

import com.jeff_media.morepersistentdatatypes.DataType;
import org.bukkit.Bukkit;
import org.bukkit.block.data.BlockData;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class BlockDataArrayDataType implements PersistentDataType<byte[], BlockData[]> {
    @NotNull
    @Override
    public Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @NotNull
    @Override
    public Class<BlockData[]> getComplexType() {
        return BlockData[].class;
    }

    @Override
    public byte @NotNull [] toPrimitive(final @NotNull BlockData[] blockData, final @NotNull PersistentDataAdapterContext context) {
        return DataType.STRING_ARRAY.toPrimitive(Arrays.stream(blockData).map(BlockData::getAsString).toArray(String[]::new),context);
    }

    @NotNull
    @Override
    public BlockData[] fromPrimitive(final @NotNull byte[] bytes, final @NotNull PersistentDataAdapterContext context) {
        return Arrays.stream(DataType.STRING_ARRAY.fromPrimitive(bytes, context)).map(Bukkit::createBlockData).toArray(BlockData[]::new);
    }
}
