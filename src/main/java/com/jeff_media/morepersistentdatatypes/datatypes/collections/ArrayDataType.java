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
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static com.jeff_media.morepersistentdatatypes.NamespacedKeyUtils.getValueKey;

@SuppressWarnings("unchecked")
public class ArrayDataType<D> implements PersistentDataType<PersistentDataContainer, D[]> {
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
        //final List<Integer> nullValues = new ArrayList<>();
        pdc.set(KEY_SIZE, DataType.INTEGER, array.length);
        for (int i = 0; i < array.length; i++) {
            final D data = array[i];
            if(data == null) {
                //nullValues.add(i);
            } else {
                pdc.set(getValueKey(i), dataType, data);
            }
        }
        //Utils.setNullValueList(pdc, nullValues);
        return pdc;
    }

    @Override
    public D @NotNull [] fromPrimitive(final @NotNull PersistentDataContainer pdc, final @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
        final Integer size = pdc.get(KEY_SIZE, DataType.INTEGER);
        //final List<Integer> nullValuesList = Utils.getNullValueList(pdc);

        if (size == null) {
            throw new IllegalArgumentException(E_NOT_AN_ARRAY);
        }
        final D[] array = (D[]) Array.newInstance(componentClazz, size);
        for (int i = 0; i < size; i++) {
            /*if(nullValuesList.contains(i)) {
                array[i] = null;
            } else {*/
                array[i] = pdc.get(getValueKey(i), dataType);
            //}
        }
        return array;
    }
}
