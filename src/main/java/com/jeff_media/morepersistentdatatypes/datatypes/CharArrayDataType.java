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

import lombok.NonNull;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;

public class CharArrayDataType implements PersistentDataType<int[], char[]> {
    @NonNull
    @Override
    public Class<int[]> getPrimitiveType() {
        return int[].class;
    }

    @NonNull
    @Override
    public Class<char[]> getComplexType() {
        return char[].class;
    }

    @Override
    public int @NonNull [] toPrimitive(final char[] chars, @NonNull final PersistentDataAdapterContext context) {
        final int[] ints = new int[chars.length];
        for (int i = 0; i < chars.length; i++) {
            ints[i] = chars[i];
        }
        return ints;
    }

    @Override
    public char @NonNull [] fromPrimitive(final int[] ints, final @NonNull PersistentDataAdapterContext context) {
        final char[] chars = new char[ints.length];
        for (int i = 0; i < ints.length; i++) {
            chars[i] = (char) ints[i];
        }
        return chars;
    }
}
