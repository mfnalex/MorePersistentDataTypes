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
import java.lang.reflect.Array;

class ConfigurationSerializableArrayDataType<T extends ConfigurationSerializable> implements PersistentDataType<byte[], T[]> {
    private final Class<T> type;
    private final Class<T[]> types;

    ConfigurationSerializableArrayDataType(Class<T> type, Class<T[]> types) {
        this.type = type;
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
    public byte @NotNull [] toPrimitive(@NotNull T @NotNull [] serializable, @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
        try (
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                BukkitObjectOutputStream bukkitObjectOutputStream = new BukkitObjectOutputStream(outputStream)
        ) {
            bukkitObjectOutputStream.writeInt(serializable.length);
            for (T t : serializable) {
                bukkitObjectOutputStream.writeObject(t);
            }
            return outputStream.toByteArray();
        }
    }

    @NotNull
    @Override
    @SneakyThrows
    @SuppressWarnings("unchecked")
    public T @NotNull [] fromPrimitive(byte @NotNull [] bytes, @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
        try (
                ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
                BukkitObjectInputStream bukkitObjectInputStream = new BukkitObjectInputStream(inputStream)
        ) {
                T[] ts = (T[]) Array.newInstance(type, bukkitObjectInputStream.readInt());
                for(int i = 0; i < ts.length; i++) {
                    ts[i] = (T) bukkitObjectInputStream.readObject();
                }
            return ts;
        }
    }
}
