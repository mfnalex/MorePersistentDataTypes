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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;

/**
 * A {@link PersistentDataType} for {@code double} arrays.
 */
public class DoubleArrayDataType implements PersistentDataType<byte[], double[]> {

    @Override
    public @NotNull Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @Override
    public @NotNull Class<double[]> getComplexType() {
        return double[].class;
    }

    @NotNull
    @Override
    public byte [] toPrimitive(final double[] doubles, final @NotNull PersistentDataAdapterContext itemTagAdapterContext) {
        try (final ByteArrayOutputStream bos = new ByteArrayOutputStream(); final DataOutputStream dos = new DataOutputStream(bos)) {
            dos.writeInt(doubles.length);
            for (final double number : doubles) {
                dos.writeDouble(number);
            }
            dos.flush();
            return bos.toByteArray();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @NotNull
    @Override
    public double [] fromPrimitive(@NotNull final byte [] bytes, @NotNull final PersistentDataAdapterContext itemTagAdapterContext) {
        try (final ByteArrayInputStream bis = new ByteArrayInputStream(bytes); final DataInputStream dis = new DataInputStream(bis)) {
            final double[] doubles = new double[dis.readInt()];
            for (int i = 0; i < doubles.length; i++) {
                doubles[i] = dis.readDouble();
            }
            return doubles;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}