package com.jeff_media.morepersistentdatatypes;

import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;


import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

/**
 *
 * @param <C> Collection
 */
public class DataCollection
        <C extends Collection<D>,D>
        implements PersistentDataType<PersistentDataContainer, C> {

    private static final String MUST_NOT_BE_NULL = "Collections stored in a PersistentDataContainer must not contain any null values.";

    private final Class<C> collectionClazz;
    private final PersistentDataType<?, D> persistentDataType;

    public DataCollection(Class<C> collectionClazz, PersistentDataType<?, D> persistentDataType) {
        this.collectionClazz = collectionClazz;
        this.persistentDataType = persistentDataType;
    }

    @NotNull
    @Override
    public PersistentDataContainer toPrimitive(@NotNull C collection, @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
        PersistentDataContainer pdc = persistentDataAdapterContext.newPersistentDataContainer();
        int index = 0;
        System.out.println(collection);
        for(D data : collection) {
            if(data == null) {
                throw new IllegalArgumentException(MUST_NOT_BE_NULL);
            }
            pdc.set(getKey(index++), persistentDataType, data);
        }
        return pdc;
    }

    @NotNull
    @Override
    public C fromPrimitive(@NotNull PersistentDataContainer pdc, @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
        C collection;
        try {
           collection = collectionClazz.getConstructor().newInstance();
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            throw new IllegalArgumentException("The given collection doesn't have a no-args constructor");
        }
        for(NamespacedKey key : pdc.getKeys()) {
            collection.add(pdc.get(key, persistentDataType));
        }
        return collection;
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
        return NamespacedKey.fromString("_:" + name);
    }
}