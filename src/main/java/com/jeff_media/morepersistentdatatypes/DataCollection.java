package com.jeff_media.morepersistentdatatypes;

import lombok.NonNull;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;


import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

class DataCollection
        <C extends Collection<D>,D>
        implements PersistentDataType<PersistentDataContainer, C> {

    private static final String E_MUST_NOT_BE_NULL = "Collections stored in a PersistentDataContainer must not contain any null values.";
    private static final String E_MUST_HAVE_NO_ARGS_CONSTRUCTOR = "The given collection class (%s) doesn't have a no-args constructor.";
    private static final String E_NOT_A_COLLECTION = "Not a collection.";
    private static final NamespacedKey KEY_SIZE = getKey("s");

    private final Class<C> collectionClazz;
    private final PersistentDataType<?, D> dataType;

    DataCollection(@NonNull final Class<C> collectionClazz, @NonNull final PersistentDataType<?, D> dataType) {
        this.collectionClazz = collectionClazz;
        this.dataType = dataType;
    }

    @NotNull
    @Override
    public PersistentDataContainer toPrimitive(@NotNull final C collection, @NotNull final PersistentDataAdapterContext context) {
        final PersistentDataContainer pdc = context.newPersistentDataContainer();
        pdc.set(KEY_SIZE,DataType.INTEGER,collection.size());
        int index = 0;
        for(final D data : collection) {
            if(data == null) {
                throw new IllegalArgumentException(E_MUST_NOT_BE_NULL);
            }
            pdc.set(getKey(index++), dataType, data);
        }
        return pdc;
    }

    @NotNull
    @Override
    public C fromPrimitive(@NotNull final PersistentDataContainer pdc, @NotNull final PersistentDataAdapterContext context) {
        try {
            final C collection = collectionClazz.getConstructor().newInstance();
            final Integer size = pdc.get(KEY_SIZE, DataType.INTEGER);
            if(size == null) {
                throw new IllegalArgumentException(E_NOT_A_COLLECTION);
            }
            for(int i = 0; i < size; i++) {
                collection.add(pdc.get(getKey(i), dataType));
            }
            return collection;
        } catch (final InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            throw new IllegalArgumentException(String.format(E_MUST_HAVE_NO_ARGS_CONSTRUCTOR,collectionClazz.getName()));
        }
    }

    @NotNull
    @Override
    public Class<PersistentDataContainer> getPrimitiveType() {
        return PersistentDataContainer.class;
    }

    @NotNull
    @Override
    public Class<C> getComplexType() {
        return collectionClazz;
    }

    private static NamespacedKey getKey(final int name) {
        return getKey(String.valueOf(name));
    }

    private static NamespacedKey getKey(final String name) {
        return NamespacedKey.fromString("v:" + name);
    }
}