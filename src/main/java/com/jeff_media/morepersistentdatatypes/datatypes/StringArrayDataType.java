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

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class StringArrayDataType implements PersistentDataType<byte[], String[]> {

    private final Charset charset;

    public StringArrayDataType(final Charset charset) {
        this.charset = charset;
    }

    @Override
    public @NotNull Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @Override
    public @NotNull Class<String[]> getComplexType() {
        return String[].class;
    }

    @Override
    public byte @NotNull [] toPrimitive(final String[] strings, @NotNull final PersistentDataAdapterContext context) {
        final byte[][] allStringBytes = new byte[strings.length][];
        int total = 0;
        for (int i = 0; i < allStringBytes.length; i++) {
            final byte[] bytes = strings[i].getBytes(charset);
            allStringBytes[i] = bytes;
            total += bytes.length;
        }

        final ByteBuffer buffer = ByteBuffer.allocate(total + allStringBytes.length * 4); //stores integers
        for (final byte[] bytes : allStringBytes) {
            buffer.putInt(bytes.length);
            buffer.put(bytes);
        }

        return buffer.array();
    }

    @Override
    public String @NotNull [] fromPrimitive(final byte @NotNull [] bytes, @NotNull final PersistentDataAdapterContext itemTagAdapterContext) {
        final ByteBuffer buffer = ByteBuffer.wrap(bytes);
        final List<String> list = new ArrayList<>();

        while (buffer.remaining() > 0) {
            if (buffer.remaining() < 4) break;
            final int stringLength = buffer.getInt();
            if (buffer.remaining() < stringLength) break;

            final byte[] stringBytes = new byte[stringLength];
            buffer.get(stringBytes);

            list.add(new String(stringBytes, charset));
        }

        return list.toArray(new String[0]);
    }
}