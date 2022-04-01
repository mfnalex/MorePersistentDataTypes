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

package com.jeff_media.morepersistentdatatypes.datatypes.serializable;

import lombok.SneakyThrows;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.lang.reflect.Array;

public class ConfigurationSerializableArrayDataType<T extends ConfigurationSerializable> implements PersistentDataType<byte[], T[]> {
    private final Class<T> type;
    private final Class<T[]> types;

    public ConfigurationSerializableArrayDataType(final Class<T[]> types) {
        //noinspection unchecked
        this.type = (Class<T>) types.getComponentType();
        this.types = types;
    }

    @NotNull
    @Override
    public Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @NotNull
    @Override
    public Class<T[]> getComplexType() {
        return types;
    }

    @Override
    @SneakyThrows
    public byte [] toPrimitive(@NotNull final T [] serializable, @NotNull final PersistentDataAdapterContext persistentDataAdapterContext) {
        try (final ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); final BukkitObjectOutputStream bukkitObjectOutputStream = new BukkitObjectOutputStream(outputStream)) {
            bukkitObjectOutputStream.writeInt(serializable.length);
            for (final T t : serializable) {
                bukkitObjectOutputStream.writeObject(t);
            }
            return outputStream.toByteArray();
        }
    }

    @NotNull
    @Override
    @SneakyThrows
    @SuppressWarnings("unchecked")
    public T [] fromPrimitive(final byte [] bytes, @NotNull final PersistentDataAdapterContext persistentDataAdapterContext) {
        try (final ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes); final BukkitObjectInputStream bukkitObjectInputStream = new BukkitObjectInputStream(inputStream)) {
            final T[] ts = (T[]) Array.newInstance(type, bukkitObjectInputStream.readInt());
            for (int i = 0; i < ts.length; i++) {
                ts[i] = (T) bukkitObjectInputStream.readObject();
            }
            return ts;
        } catch (final EOFException e) {
            return (T[]) Array.newInstance(getComplexType().getComponentType(), 0);
        }
    }
}
