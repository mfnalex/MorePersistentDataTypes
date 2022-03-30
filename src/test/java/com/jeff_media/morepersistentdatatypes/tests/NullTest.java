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

package com.jeff_media.morepersistentdatatypes.tests;

import com.jeff_media.morepersistentdatatypes.DataType;
import com.jeff_media.morepersistentdatatypes.MorePersistentDataTypesUnitTest;
import com.jeff_media.morepersistentdatatypes.TestData;
import org.junit.jupiter.api.Test;

import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NullTest extends MorePersistentDataTypesUnitTest {

    @Test
    void testNullArrays() {
        pdc.set(key, DataType.asArray(TestData.NULLABLE_INTEGER_ARRAY, DataType.INTEGER), TestData.NULLABLE_INTEGER_ARRAY);
        pdc.set(key2, DataType.asArray(TestData.NULLABLE_STRING_ARRAY, DataType.STRING), TestData.NULLABLE_STRING_ARRAY);
        assertArrayEquals(TestData.NULLABLE_INTEGER_ARRAY, pdc.get(key, DataType.asArray(new Integer[0], DataType.INTEGER)));
        assertArrayEquals(TestData.NULLABLE_STRING_ARRAY, pdc.get(key2, DataType.asArray(new String[0], DataType.STRING)));
    }

    @Test
    void testNullList() {
        pdc.set(key, DataType.asList(DataType.INTEGER), Arrays.asList(TestData.NULLABLE_INTEGER_ARRAY));
        pdc.set(key2, DataType.asList(DataType.STRING), Arrays.asList(TestData.NULLABLE_STRING_ARRAY));
        assertEquals(Arrays.asList(TestData.NULLABLE_INTEGER_ARRAY), pdc.get(key, DataType.asList(DataType.INTEGER)));
        assertEquals(Arrays.asList(TestData.NULLABLE_STRING_ARRAY), pdc.get(key2, DataType.asList(DataType.STRING)));
    }

    @Test
    void testNullMap() {
        final HashMap<String,Integer> hashMap = new HashMap<>();
        hashMap.put("a",1);
        hashMap.put("b",null);
        hashMap.put("c",3);
        hashMap.put("d",null);
        hashMap.put("e",5);
        pdc.set(key, DataType.asMap(DataType.STRING, DataType.INTEGER),hashMap);
        final EnumMap<StandardCopyOption, Integer> enumMap = new EnumMap<>(StandardCopyOption.class);
        enumMap.put(StandardCopyOption.ATOMIC_MOVE,1);
        enumMap.put(StandardCopyOption.REPLACE_EXISTING,null);
        enumMap.put(StandardCopyOption.COPY_ATTRIBUTES,3);
        pdc.set(key2, DataType.asMap(DataType.asEnum(StandardCopyOption.class),DataType.INTEGER),enumMap);
        assertEquals(hashMap,pdc.get(key, DataType.asHashMap(DataType.STRING, DataType.INTEGER)));
        assertEquals(enumMap, pdc.get(key2, DataType.asMap(DataType.asEnum(StandardCopyOption.class),DataType.INTEGER)));
    }
}
