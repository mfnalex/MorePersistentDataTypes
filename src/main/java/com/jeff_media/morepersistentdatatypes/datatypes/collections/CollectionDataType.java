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

import java.util.Collection;
import java.util.function.Supplier;

import static com.jeff_media.morepersistentdatatypes.DataType.Utils.getValueKey;

/**
 * A {@link PersistentDataType} for {@link Collection}s
 * @param <C> The type of the collection
 * @param <T> The type of the collection's elements
 */
public class CollectionDataType<C extends Collection<T>, T> implements PersistentDataType<PersistentDataContainer, C> {

    private static final String E_NOT_A_COLLECTION = "Not a collection.";
    private static final NamespacedKey KEY_SIZE = getValueKey("s");

    private final Supplier<? extends C> collectionSupplier;
    private final Class<C> collectionClazz;
    private final PersistentDataType<?, T> dataType;

    /**
     * Creates a new {@link CollectionDataType} with the given collection supplier and element data type
     * @param supplier The collection supplier
     * @param dataType The element data type
     */
    @SuppressWarnings("unchecked")
    public CollectionDataType(@NotNull final Supplier<C> supplier,
                              @NotNull final PersistentDataType<?, T> dataType) {
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
        pdc.set(KEY_SIZE, DataType.INTEGER, collection.size());
        int index = 0;
        for (final T data : collection) {
            if (data != null) {
                pdc.set(getValueKey(index), dataType, data);
            }
            index++;
        }
        return pdc;
    }

    @NotNull
    @Override
    public C fromPrimitive(@NotNull final PersistentDataContainer pdc, @NotNull final PersistentDataAdapterContext context) {
        final C collection = collectionSupplier.get();
        final Integer size = pdc.get(KEY_SIZE, DataType.INTEGER);
        if (size == null) {
            throw new IllegalArgumentException(E_NOT_A_COLLECTION);
        }
        for (int i = 0; i < size; i++) {
                collection.add(pdc.get(getValueKey(i), dataType));
        }
        return collection;
    }

}