/*
 * Copyright (c) 2022 Alexander Majka (mfnalex) / JEFF Media GbR
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.jeff_media.morepersistentdatatypes.datatypes;

import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import lombok.NonNull;

import java.nio.ByteBuffer;
import java.util.UUID;

public class UuidDataType implements PersistentDataType<byte[], UUID> {

    @Override
    public @NonNull Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @Override
    public @NonNull Class<UUID> getComplexType() {
        return UUID.class;
    }

    @Override
    public byte @NonNull [] toPrimitive(final UUID complex, @NonNull final PersistentDataAdapterContext context) {
        final ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(complex.getMostSignificantBits());
        bb.putLong(complex.getLeastSignificantBits());
        return bb.array();
    }

    @Override
    public @NonNull UUID fromPrimitive(final byte @NonNull [] primitive, @NonNull final PersistentDataAdapterContext context) {
        final ByteBuffer bb = ByteBuffer.wrap(primitive);
        final long firstLong = bb.getLong();
        final long secondLong = bb.getLong();
        return new UUID(firstLong, secondLong);
    }
}