/*
 * Copyright (c) 2022 Alexander Majka (mfnalex) / JEFF Media GbR
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License.
 *
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * If you need help or have any suggestions, feel free to join my Discord and head to #programming-help:
 *
 * Discord: https://discord.jeff-media.com/
 *
 * If you find this library helpful or if you're using it one of your paid plugins, please consider leaving a donation
 * to support the further development of this project :)
 *
 * Donations: https://paypal.me/mfnalex
 */

package com.jeff_media.morepersistentdatatypes.datatypes;

import com.jeff_media.morepersistentdatatypes.DataType;
import org.bukkit.Bukkit;
import org.bukkit.block.data.BlockData;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * A {@link PersistentDataType} for {@link BlockData} arrays.
 */
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

    @NotNull
    @Override
    public byte [] toPrimitive(@NotNull final BlockData [] blockData, final @NotNull PersistentDataAdapterContext context) {
        return DataType.STRING_ARRAY.toPrimitive(Arrays.stream(blockData).map(BlockData::getAsString).toArray(String[]::new),context);
    }

    @NotNull
    @Override
    public BlockData [] fromPrimitive(@NotNull final byte [] bytes, final @NotNull PersistentDataAdapterContext context) {
        return Arrays.stream(DataType.STRING_ARRAY.fromPrimitive(bytes, context)).map(Bukkit::createBlockData).toArray(BlockData[]::new);
    }
}
