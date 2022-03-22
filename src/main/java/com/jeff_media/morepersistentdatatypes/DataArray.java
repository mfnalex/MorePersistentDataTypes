package com.jeff_media.morepersistentdatatypes;

import lombok.NonNull;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;


import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;

class DataArray
        <D>
        implements PersistentDataType<PersistentDataContainer, D[]> {

    private static final String E_NOT_AN_ARRAY = "Not an array.";
    private static final NamespacedKey KEY_SIZE = getKey("s");

    private final PersistentDataType<?, D> dataType;
    private final Class<D> componentType;

    DataArray(@NonNull final PersistentDataType<?, D> dataType) {
        this.dataType = dataType;
        this.componentType = dataType.getComplexType();
    }

    @NotNull
    @Override
    public PersistentDataContainer toPrimitive(@NotNull final D[] array, @NotNull final PersistentDataAdapterContext context) {
        PersistentDataContainer pdc = context.newPersistentDataContainer();
        pdc.set(KEY_SIZE,DataType.INTEGER,array.length);
        int index = 0;
        for(D data : array) {
            pdc.set(getKey(index++), dataType, data);
        }
        return pdc;
    }

    @NotNull
    @Override
    public Class<D[]> getComplexType() {
        return D[].class; // why doesn't this work :'(((
    }

    @NotNull
    @Override
    public D[] fromPrimitive(@NotNull final PersistentDataContainer pdc, @NotNull final PersistentDataAdapterContext context) {
        Integer size = pdc.get(KEY_SIZE, DataType.INTEGER);
        if(size == null) {
            throw new IllegalArgumentException(E_NOT_AN_ARRAY);
        }
        final D[] array = (D[]) Array.newInstance(componentType,size);
        for(int i = 0; i < size; i++) {
            array[i] = pdc.get(getKey(i),dataType);
        }
        return array;
    }

    @NotNull
    @Override
    public Class<PersistentDataContainer> getPrimitiveType() {
        return PersistentDataContainer.class;
    }

    private static NamespacedKey getKey(final int name) {
        return getKey(String.valueOf(name));
    }

    private static NamespacedKey getKey(final String name) {
        return NamespacedKey.fromString("v:" + name);
    }
}