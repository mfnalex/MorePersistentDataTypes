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

import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * A {@link PersistentDataType} for {@link String} arrays
 */
public class StringArrayDataType implements PersistentDataType<byte[], String[]> {

    private final Charset charset;

    /**
     * Creates a new {@link StringArrayDataType} with the given charset
     *
     * @param charset The charset to use
     */
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

    @NotNull
    @Override
    public byte [] toPrimitive(final String[] strings, @NotNull final PersistentDataAdapterContext context) {
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

    @NotNull
    @Override
    public String [] fromPrimitive(@NotNull final byte [] bytes, @NotNull final PersistentDataAdapterContext itemTagAdapterContext) {
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