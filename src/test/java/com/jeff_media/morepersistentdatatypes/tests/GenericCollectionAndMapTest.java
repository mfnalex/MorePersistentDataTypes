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
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GenericCollectionAndMapTest extends MorePersistentDataTypesUnitTest {
    @Test
    void testGenericMap() {
        final HashMap<String,String> original = new HashMap<>();
        original.put("a","b");
        original.put("1","2");
        pdc.set(key, DataType.asGenericMap(HashMap.class, HashMap<String,String>::new, DataType.STRING, DataType.STRING), original);
        HashMap<String,String> stored = (HashMap<String, String>) pdc.get(key, DataType.asGenericMap(HashMap.class, HashMap<String,String>::new, DataType.STRING, DataType.STRING));
        assertEquals(original, stored);
    }

    @Test
    void testGenericCollection() {
        final Vector<String> original = new Vector<>();
        original.add("a");
        original.add("b");
        pdc.set(key, DataType.asGenericCollection(Vector.class, Vector<String>::new, DataType.STRING), original);
        Vector<String> stored = (Vector<String>) pdc.get(key, DataType.asGenericCollection(Vector.class, Vector<String>::new, DataType.STRING));
        assertEquals(original, stored);
    }
}
