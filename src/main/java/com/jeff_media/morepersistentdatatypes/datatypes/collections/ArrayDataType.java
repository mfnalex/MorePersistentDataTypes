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
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;

import static com.jeff_media.morepersistentdatatypes.NamespacedKeyUtils.getValueKey;

@SuppressWarnings("unchecked")
public class ArrayDataType<D> implements PersistentDataType<PersistentDataContainer, D[]> {
    private static final String E_MUST_NOT_BE_NULL = "Arrays stored in a PersistentDataContainer must not contain any null values.";
    private static final String E_NOT_AN_ARRAY = "Not an array.";
    private static final NamespacedKey KEY_SIZE = getValueKey("s");

    private final Class<D[]> arrayClazz;
    private final Class<D> componentClazz;
    private final PersistentDataType<?, D> dataType;

    public ArrayDataType(final @NotNull D[] array, final @NotNull PersistentDataType<?, D> dataType) {
        this.arrayClazz = (Class<D[]>) array.getClass();
        this.componentClazz = (Class<D>) array.getClass().getComponentType();
        this.dataType = dataType;
    }


    @NotNull
    @Override
    public Class<PersistentDataContainer> getPrimitiveType() {
        return PersistentDataContainer.class;
    }

    @NotNull
    @Override
    public Class<D[]> getComplexType() {
        return arrayClazz;
    }

    @NotNull
    @Override
    public PersistentDataContainer toPrimitive(final D @NotNull [] array, final @NotNull PersistentDataAdapterContext context) {
        final PersistentDataContainer pdc = context.newPersistentDataContainer();
        pdc.set(KEY_SIZE, DataType.INTEGER, array.length);
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) throw new IllegalArgumentException(E_MUST_NOT_BE_NULL);
            pdc.set(getValueKey(i), dataType, array[i]);
        }
        return pdc;
    }

    @Override
    public D @NotNull [] fromPrimitive(final @NotNull PersistentDataContainer pdc, final @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
        final Integer size = pdc.get(KEY_SIZE, DataType.INTEGER);
        if (size == null) {
            throw new IllegalArgumentException(E_NOT_AN_ARRAY);
        }
        final D[] array = (D[]) Array.newInstance(componentClazz, size);
        for (int i = 0; i < size; i++) {
            array[i] = pdc.get(getValueKey(i), dataType);
        }
        return array;
    }
}
