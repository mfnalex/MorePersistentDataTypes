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
        for (int i = 0; i < booleans.length; i++) {
            bytes[i] = (byte) (booleans[i] ? 1 : 0);
        }
        return bytes;
    }

    @Override
    public boolean @NotNull [] fromPrimitive(final byte[] bytes, final @NotNull PersistentDataAdapterContext context) {
        final boolean[] booleans = new boolean[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            booleans[i] = bytes[i] != 0;
        }
        return booleans;
    }
}
