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
import com.jeff_media.morepersistentdatatypes.NamespacedKeyUtils;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Utils {

    static int @NotNull [] listToIntArray(final @NotNull List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    @NotNull
    static List<Integer> intArrayToList(final int @NotNull [] array) {
        return IntStream.of(array).boxed().collect(Collectors.toList());
    }

    public static List<Integer> getNullValueList(PersistentDataContainer pdc) {
        return intArrayToList(pdc.getOrDefault(NamespacedKeyUtils.KEY_NULL, DataType.INTEGER_ARRAY, new int[0]));
    }

    public static void setNullValueList(PersistentDataContainer pdc, List<Integer> nullValues) {
        if(!nullValues.isEmpty()) {
            pdc.set(NamespacedKeyUtils.KEY_NULL, DataType.INTEGER_ARRAY, Utils.listToIntArray(nullValues));
        }
    }
}
