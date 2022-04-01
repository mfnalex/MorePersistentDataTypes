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

public class BooleanArrayDataType implements PersistentDataType<byte[], boolean[]> {
    @NonNull
    @Override
    public Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @NonNull
    @Override
    public Class<boolean[]> getComplexType() {
        return boolean[].class;
    }

    @Override
    public byte @NonNull [] toPrimitive(final boolean[] booleans, final @NonNull PersistentDataAdapterContext context) {
        final byte[] bytes = new byte[booleans.length];
        for (int i = 0; i < booleans.length; i++) {
            bytes[i] = (byte) (booleans[i] ? 1 : 0);
        }
        return bytes;
    }

    @Override
    public boolean @NonNull [] fromPrimitive(final byte[] bytes, final @NonNull PersistentDataAdapterContext context) {
        final boolean[] booleans = new boolean[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            booleans[i] = bytes[i] != 0;
        }
        return booleans;
    }
}
