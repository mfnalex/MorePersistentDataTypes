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

package com.jeff_media.morepersistentdatatypes.datatypes.collections;

import com.jeff_media.morepersistentdatatypes.DataType;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;

import static com.jeff_media.morepersistentdatatypes.DataType.Utils.getValueKey;

/**
 * A {@link PersistentDataType} for arrays
 *
 * @param <T> The type of the array
 */
@SuppressWarnings("unchecked")
public class ArrayDataType<T> implements PersistentDataType<PersistentDataContainer, T[]> {
    private static final String E_NOT_AN_ARRAY = "Not an array.";
    private static final NamespacedKey KEY_SIZE = getValueKey("s");

    private final Class<T[]> arrayClazz;
    private final Class<T> componentClazz;
    private final PersistentDataType<?, T> dataType;

    /**
     * Creates a new ArrayDataType
     * @param array The array to get the class from
     * @param dataType The {@link PersistentDataType} of the array's elements
     */
    public ArrayDataType(final @NotNull T[] array, final @NotNull PersistentDataType<?, T> dataType) {
        this.arrayClazz = (Class<T[]>) array.getClass();
        this.componentClazz = (Class<T>) array.getClass().getComponentType();
        this.dataType = dataType;
    }


    @NotNull
    @Override
    public Class<PersistentDataContainer> getPrimitiveType() {
        return PersistentDataContainer.class;
    }

    @NotNull
    @Override
    public Class<T[]> getComplexType() {
        return arrayClazz;
    }

    @NotNull
    @Override
    public PersistentDataContainer toPrimitive(final T[] array, final @NotNull PersistentDataAdapterContext context) {
        final PersistentDataContainer pdc = context.newPersistentDataContainer();
        pdc.set(KEY_SIZE, DataType.INTEGER, array.length);
        for (int i = 0; i < array.length; i++) {
            final T data = array[i];
            if(data != null) {
                pdc.set(getValueKey(i), dataType, data);
            }
        }
        return pdc;
    }

    @NotNull
    @Override
    public T[] fromPrimitive(final @NotNull PersistentDataContainer pdc, final @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
        final Integer size = pdc.get(KEY_SIZE, DataType.INTEGER);

        if (size == null) {
            throw new IllegalArgumentException(E_NOT_AN_ARRAY);
        }
        final T[] array = (T[]) Array.newInstance(componentClazz, size);
        for (int i = 0; i < size; i++) {
                array[i] = pdc.get(getValueKey(i), dataType);
        }
        return array;
    }
}
