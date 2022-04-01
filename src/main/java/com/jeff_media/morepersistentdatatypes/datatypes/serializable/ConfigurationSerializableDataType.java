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

public class ConfigurationSerializableDataType<T extends ConfigurationSerializable> implements PersistentDataType<byte[], T> {
    private final Class<T> type;

    public ConfigurationSerializableDataType(final Class<T> type) {
        this.type = type;
    }

    @NotNull
    @Override
    public Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @NotNull
    @Override
    public Class<T> getComplexType() {
        return type;
    }

    @Override
    @SneakyThrows
    public byte [] toPrimitive(@NotNull final T serializable, @NotNull final PersistentDataAdapterContext persistentDataAdapterContext) {
        try (final ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); final BukkitObjectOutputStream bukkitObjectOutputStream = new BukkitObjectOutputStream(outputStream)) {
            bukkitObjectOutputStream.writeObject(serializable);
            return outputStream.toByteArray();
        }
    }

    @NotNull
    @Override
    @SneakyThrows
    @SuppressWarnings("unchecked")
    public T fromPrimitive(final byte [] bytes, @NotNull final PersistentDataAdapterContext persistentDataAdapterContext) {
        try (final ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes); final BukkitObjectInputStream bukkitObjectInputStream = new BukkitObjectInputStream(inputStream)) {
            return (T) bukkitObjectInputStream.readObject();
        }
    }
}
