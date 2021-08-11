package de.jeff_media.morepersistentdatatypes;

import lombok.SneakyThrows;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class ConfigurationSerializableDataType <T extends ConfigurationSerializable> implements PersistentDataType<byte[], T> {
    private final Class<T> type;

    public ConfigurationSerializableDataType(Class<T> type) {
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
    public byte @NotNull [] toPrimitive(@NotNull T serializable, @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
        try (
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                BukkitObjectOutputStream bukkitObjectOutputStream = new BukkitObjectOutputStream(outputStream)
        ) {
            bukkitObjectOutputStream.writeObject(serializable);
            return outputStream.toByteArray();
        }
    }

    @NotNull
    @Override
    @SneakyThrows
    @SuppressWarnings("unchecked")
    public T fromPrimitive(byte @NotNull [] bytes, @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
        try (
                ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
                BukkitObjectInputStream bukkitObjectInputStream = new BukkitObjectInputStream(inputStream)
        ) {
            return (T) bukkitObjectInputStream.readObject();
        }
    }
}
