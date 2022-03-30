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

package com.jeff_media.morepersistentdatatypes.datatypes.collections;

import com.jeff_media.morepersistentdatatypes.DataType;
import lombok.NonNull;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static com.jeff_media.morepersistentdatatypes.NamespacedKeyUtils.getKeyKey;
import static com.jeff_media.morepersistentdatatypes.NamespacedKeyUtils.getValueKey;

public class MapDataType<M extends Map<K, V>, K, V> implements PersistentDataType<PersistentDataContainer, M> {

    private static final String E_KEY_MUST_NOT_BE_NULL = "Maps stored in a PersistentDataContainer must not contain any null keys.";
    private static final String E_NOT_A_MAP = "Not a map.";
    private static final NamespacedKey KEY_SIZE = getKeyKey("s");


    private final Class<M> mapClazz;
    private final Supplier<? extends M> mapSupplier;
    private final PersistentDataType<?, K> keyDataType;
    private final PersistentDataType<?, V> valueDataType;

    public MapDataType(@NonNull final Supplier<? extends M> supplier,
                       @NonNull final PersistentDataType<?, K> keyDataType,
                       @NonNull final PersistentDataType<?, V> valueDataType) {
        this.mapSupplier = supplier;
        //noinspection unchecked
        this.mapClazz = (Class<M>) supplier.get().getClass();
        this.keyDataType = keyDataType;
        this.valueDataType = valueDataType;
    }

    @NotNull
    @Override
    public Class<PersistentDataContainer> getPrimitiveType() {
        return PersistentDataContainer.class;
    }

    @NotNull
    @Override
    public Class<M> getComplexType() {
        return mapClazz;
    }

    @NotNull
    @Override
    public PersistentDataContainer toPrimitive(@NotNull final M map, @NotNull final PersistentDataAdapterContext context) {
        final PersistentDataContainer pdc = context.newPersistentDataContainer();
        int index = 0;
        final int size = map.size();
        //final List<Integer> nullValues = new ArrayList<>();
        pdc.set(KEY_SIZE, DataType.INTEGER, size);
        for (final K key : map.keySet()) {
            if (key == null) {
                throw new IllegalArgumentException(E_KEY_MUST_NOT_BE_NULL);
            }
            final V value = map.get(key);
            if (value == null) {
                //nullValues.add(index);
            } else {
                pdc.set(getValueKey(index), valueDataType, value);
            }
            //Utils.setNullValueList(pdc, nullValues);
            pdc.set(getKeyKey(index++), keyDataType, key);
        }
        return pdc;
    }

    @NotNull
    @Override
    public M fromPrimitive(@NotNull final PersistentDataContainer pdc, @NotNull final PersistentDataAdapterContext context) {
        final M map = mapSupplier.get();
        final Integer size = pdc.get(KEY_SIZE, DataType.INTEGER);
        //final List<Integer> nullValues = Utils.getNullValueList(pdc);
        if (size == null) {
            throw new IllegalArgumentException(E_NOT_A_MAP);
        }
        for (int i = 0; i < size; i++) {
            final K key = pdc.get(getKeyKey(i), keyDataType);
            /*if(nullValues.contains(i)) {
                map.put(key, null);
            } else {*/
                map.put(key, pdc.get(getValueKey(i), valueDataType));
            //}
        }
        return map;
    }
}