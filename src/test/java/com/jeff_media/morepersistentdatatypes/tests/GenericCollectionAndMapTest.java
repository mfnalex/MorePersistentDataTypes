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

package com.jeff_media.morepersistentdatatypes.tests;

import com.jeff_media.morepersistentdatatypes.DataType;
import com.jeff_media.morepersistentdatatypes.UnitTest;
import org.bukkit.persistence.PersistentDataType;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GenericCollectionAndMapTest extends UnitTest {
    @Test
    void testGenericMap() {
        final HashMap<String,String> original = new HashMap<>();
        original.put("a","b");
        original.put("1","2");
        pdc.set(key, DataType.asGenericMap(HashMap<String,String>::new, DataType.STRING, DataType.STRING), original);
        final HashMap<String,String> stored = pdc.get(key, DataType.asGenericMap(HashMap<String,String>::new, DataType.STRING, DataType.STRING));
        assertEquals(original, stored);
    }

    @Test
    void testGenericCollection() {
        final Vector<String> original = new Vector<>();
        original.add("a");
        original.add("b");
        pdc.set(key, DataType.asGenericCollection(Vector<String>::new, DataType.STRING), original);
        final Vector<String> stored = pdc.get(key, DataType.<Vector<String>,String>asGenericCollection(Vector::new, DataType.STRING));
        assertEquals(original, stored);
    }

    @Test
    void testJavaDocGenericMapExample() {
        final Hashtable<String,Integer> original = new Hashtable<>();
        original.put("asd",123);
        final PersistentDataType<?,Hashtable<String,Integer>> dataType = DataType.asGenericMap(Hashtable::new, DataType.STRING, DataType.INTEGER);
        pdc.set(key, dataType, original);
        assertEquals(original, pdc.get(key, dataType));
    }

    @Test
    void testJavaDocGenericCollectionExample() {
        final CopyOnWriteArrayList<String> original = new CopyOnWriteArrayList<>();
        original.add("asd");
        final PersistentDataType<?,CopyOnWriteArrayList<String>> dataType = DataType.asGenericCollection(CopyOnWriteArrayList::new, DataType.STRING);
        pdc.set(key, dataType, original);
        assertEquals(original, pdc.get(key, dataType));
    }
}
