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

import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class CharArrayDataType implements PersistentDataType<int[], char[]> {
    @NotNull
    @Override
    public Class<int[]> getPrimitiveType() {
        return int[].class;
    }

    @NotNull
    @Override
    public Class<char[]> getComplexType() {
        return char[].class;
    }

    @Override
    public int @NotNull [] toPrimitive(final char[] chars, @NotNull final PersistentDataAdapterContext context) {
        final int[] ints = new int[chars.length];
        for (int i = 0; i < chars.length; i++) {
            ints[i] = chars[i];
        }
        return ints;
    }

    @Override
    public char @NotNull [] fromPrimitive(final int[] ints, final @NotNull PersistentDataAdapterContext context) {
        final char[] chars = new char[ints.length];
        for (int i = 0; i < ints.length; i++) {
            chars[i] = (char) ints[i];
        }
        return chars;
    }
}
