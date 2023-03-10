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

package com.jeff_media.morepersistentdatatypes.datatypes.serializable;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;

/**
 * A {@link PersistentDataType} for {@link ConfigurationSerializable}s
 * @param <T> The type of the {@link ConfigurationSerializable}
 */
public class ConfigurationSerializableDataType<T extends ConfigurationSerializable> implements PersistentDataType<byte[], T> {
    private final Class<T> type;

    /**
     * Creates a new {@link ConfigurationSerializableDataType} for the given type
     * @param type The type of the {@link ConfigurationSerializable}
     */
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

    @NotNull
    @Override
    public byte [] toPrimitive(@NotNull final T serializable, @NotNull final PersistentDataAdapterContext persistentDataAdapterContext) {
        try (final ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); final BukkitObjectOutputStream bukkitObjectOutputStream = new BukkitObjectOutputStream(outputStream)) {
            bukkitObjectOutputStream.writeObject(serializable);
            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new UncheckedIOException(getExceptionMessage(type, SerializationType.SERIALIZATION), e);
        }
    }

    @NotNull
    @Override
    @SuppressWarnings("unchecked")
    public T fromPrimitive(@NotNull final byte [] bytes, @NotNull final PersistentDataAdapterContext persistentDataAdapterContext) {
        try (final ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes); final BukkitObjectInputStream bukkitObjectInputStream = new BukkitObjectInputStream(inputStream)) {
            return (T) bukkitObjectInputStream.readObject();
        } catch (IOException e) {
            throw new UncheckedIOException(getExceptionMessage(type, SerializationType.DESERIALIZATION), e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(getExceptionMessage(type, SerializationType.DESERIALIZATION), e);
        }
    }

    private static boolean isBukkitClass(final Class<?> clazz) {
        return clazz.getPackage().getName().startsWith("org.bukkit.");
    }

    static String getExceptionMessage(Class<? extends ConfigurationSerializable> type, SerializationType serializationType) {
        String msg = "Could not " + serializationType + " object of type " + type.getName() + ".";
        if(!isBukkitClass(type)) {
            msg += " This is not a bug in MorePersistentDataTypes, but a bug in your " + serializationType + ".";
            if(serializationType == SerializationType.DESERIALIZATION) {
                msg += " Make sure that your class is properly registered for deserialization using org.bukkit.configuration.serialization.ConfigurationSerialization#registerClass(Class).";
            }
        }
        return msg;
    }

    enum SerializationType {
        SERIALIZATION("serialization"),
        DESERIALIZATION("deserialization");

        private final String fancyName;

        SerializationType(String fancyName) {
            this.fancyName = fancyName;
        }

        @Override
        public String toString() {
            return fancyName;
        }
    }
}
