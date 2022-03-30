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
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

import static com.jeff_media.morepersistentdatatypes.NamespacedKeyUtils.getValueKey;

public class CollectionDataType<C extends Collection<D>, D> implements PersistentDataType<PersistentDataContainer, C> {

    private static final String E_NOT_A_COLLECTION = "Not a collection.";
    private static final NamespacedKey KEY_SIZE = getValueKey("s");

    private final Supplier<? extends C> collectionSupplier;
    private final Class<C> collectionClazz;
    private final PersistentDataType<?, D> dataType;

    public CollectionDataType(@NotNull final Supplier<C> supplier,
                              @NonNull final PersistentDataType<?, D> dataType) {
        //noinspection unchecked
        this.collectionClazz = (Class<C>) supplier.get().getClass();
        this.collectionSupplier = supplier;
        this.dataType = dataType;
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

    @NotNull
    @Override
    public PersistentDataContainer toPrimitive(@NotNull final C collection, @NotNull final PersistentDataAdapterContext context) {
        final PersistentDataContainer pdc = context.newPersistentDataContainer();
        //final List<Integer> nullValues = new ArrayList<>();
        pdc.set(KEY_SIZE, DataType.INTEGER, collection.size());
        int index = 0;
        for (final D data : collection) {
            if (data == null) {
                //nullValues.add(index);
            } else {
                pdc.set(getValueKey(index), dataType, data);
            }
            index++;
        }
        //Utils.setNullValueList(pdc, nullValues);
        return pdc;
    }

    @NotNull
    @Override
    public C fromPrimitive(@NotNull final PersistentDataContainer pdc, @NotNull final PersistentDataAdapterContext context) {
        final C collection = (C) collectionSupplier.get();
        final Integer size = pdc.get(KEY_SIZE, DataType.INTEGER);
        //final List<Integer> nullValues = Utils.getNullValueList(pdc);
        if (size == null) {
            throw new IllegalArgumentException(E_NOT_A_COLLECTION);
        }
        for (int i = 0; i < size; i++) {
            /*if(nullValues.contains(i)) {
                collection.add(null);
            } else {*/
                collection.add(pdc.get(getValueKey(i), dataType));
            //}
        }
        return collection;
    }

}