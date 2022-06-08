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

import lombok.SneakyThrows;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class FloatArrayDataType implements PersistentDataType<byte[], float[]> {

    @Override
    public @NotNull Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @Override
    public @NotNull Class<float[]> getComplexType() {
        return float[].class;
    }

    @Override
    @SneakyThrows
    public byte [] toPrimitive(final float[] floats, @NotNull final PersistentDataAdapterContext itemTagAdapterContext) {
        try (final ByteArrayOutputStream bos = new ByteArrayOutputStream(); final DataOutputStream dos = new DataOutputStream(bos)) {
            dos.writeInt(floats.length);
            for (final float number : floats) {
                dos.writeFloat(number);
            }
            dos.flush();
            return bos.toByteArray();
        }
    }

    @Override
    @SneakyThrows
    public float [] fromPrimitive(final byte [] bytes, @NotNull final PersistentDataAdapterContext itemTagAdapterContext) {
        try (final ByteArrayInputStream bis = new ByteArrayInputStream(bytes); final DataInputStream dis = new DataInputStream(bis)) {
            final float[] floats = new float[dis.readInt()];
            for (int i = 0; i < floats.length; i++) {
                floats[i] = dis.readFloat();
            }
            return floats;
        }
    }
}