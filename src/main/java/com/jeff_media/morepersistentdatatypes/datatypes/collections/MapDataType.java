/*
 * Copyright (c) 2022 Alexander Majka (mfnalex) / JEFF Media GbR
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of  MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.jeff_media.morepersistentdatatypes.datatypes.collections;

import com.jeff_media.morepersistentdatatypes.DataType;
import lombok.NonNull;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import static com.jeff_media.morepersistentdatatypes.NamespacedKeyUtils.getKeyKey;
import static com.jeff_media.morepersistentdatatypes.NamespacedKeyUtils.getValueKey;

public class MapDataType<M extends Map<K, V>, K, V> implements PersistentDataType<PersistentDataContainer, M> {

    private static final String E_KEY_MUST_NOT_BE_NULL = "Maps stored in a PersistentDataContainer must not contain any null keys.";
    private static final String E_VALUE_MUST_NOT_BE_NULL = "Maps stored in a PersistentDataContainer must not contain any null values.";
    private static final String E_MUST_HAVE_NO_ARGS_CONSTRUCTOR = "The given map class (%s) doesn't have a no-args constructor.";
    private static final String E_NOT_A_MAP = "Not a map.";
    private static final NamespacedKey KEY_SIZE = getKeyKey("s");


    private final Class<M> mapClazz;
    private final PersistentDataType<?, K> keyDataType;
    private final PersistentDataType<?, V> valueDataType;

    public MapDataType(@NonNull final Class<M> mapClazz, @NonNull final PersistentDataType<?, K> keyDataType, @NonNull final PersistentDataType<?, V> valueDataType) {
        this.mapClazz = mapClazz;
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
        pdc.set(KEY_SIZE, DataType.INTEGER, size);
        for (final K key : map.keySet()) {
            if (key == null) {
                throw new IllegalArgumentException(E_KEY_MUST_NOT_BE_NULL);
            }
            final V value = map.get(key);
            if (value == null) {
                throw new IllegalArgumentException(E_VALUE_MUST_NOT_BE_NULL);
            }
            pdc.set(getKeyKey(index), keyDataType, key);
            pdc.set(getValueKey(index++), valueDataType, value);
        }
        return pdc;
    }

    @NotNull
    @Override
    public M fromPrimitive(@NotNull final PersistentDataContainer pdc, @NotNull final PersistentDataAdapterContext context) {
        try {
            final M map = mapClazz.getConstructor().newInstance();
            final Integer size = pdc.get(KEY_SIZE, DataType.INTEGER);
            if (size == null) {
                throw new IllegalArgumentException(E_NOT_A_MAP);
            }
            for (int i = 0; i < size; i++) {
                map.put(pdc.get(getKeyKey(i), keyDataType), pdc.get(getValueKey(i), valueDataType));
            }
            return map;
        } catch (final InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            throw new IllegalArgumentException(String.format(E_MUST_HAVE_NO_ARGS_CONSTRUCTOR, mapClazz.getName()));
        }
    }
}