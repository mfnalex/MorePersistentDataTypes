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

import lombok.SneakyThrows;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ShortArrayDataType implements PersistentDataType<byte[], short[]> {

    @Override
    public @NotNull Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @Override
    public @NotNull Class<short[]> getComplexType() {
        return short[].class;
    }

    @Override
    @SneakyThrows
    public byte @NotNull [] toPrimitive(final short[] shorts, @NotNull final PersistentDataAdapterContext itemTagAdapterContext) {
        try (final ByteArrayOutputStream bos = new ByteArrayOutputStream(); final DataOutputStream dos = new DataOutputStream(bos)) {
            dos.writeInt(shorts.length);
            for (final short number : shorts) {
                dos.writeShort(number);
            }
            dos.flush();
            return bos.toByteArray();
        }
    }

    @Override
    @SneakyThrows
    public short @NotNull [] fromPrimitive(final byte @NotNull [] bytes, @NotNull final PersistentDataAdapterContext itemTagAdapterContext) {
        try (final ByteArrayInputStream bis = new ByteArrayInputStream(bytes); final DataInputStream dis = new DataInputStream(bis)) {
            final short[] shorts = new short[dis.readInt()];
            for (int i = 0; i < shorts.length; i++) {
                shorts[i] = dis.readShort();
            }
            return shorts;
        }
    }
}