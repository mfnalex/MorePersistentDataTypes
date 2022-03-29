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
import org.bukkit.persistence.PersistentDataType;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CollectionTest extends MorePersistentDataTypesUnitTest {

    @Test
    void testNestedCollections() {
        final Set<ArrayList<LinkedList<String>>> original = new HashSet<>();
        final ArrayList<LinkedList<String>> arrayList1 = new ArrayList<>();
        final ArrayList<LinkedList<String>> arrayList2 = new ArrayList<>();
        final LinkedList<String> linkedList1 = new LinkedList<>();
        linkedList1.add("1");
        linkedList1.add("2");
        final LinkedList<String> linkedList2 = new LinkedList<>();
        linkedList2.add("3");
        arrayList1.add(linkedList1);
        arrayList1.add(linkedList2);
        arrayList2.add(linkedList1);
        original.add(arrayList1);
        original.add(arrayList2);
        pdc.set(key, DataType.asSet(DataType.asArrayList(DataType.asLinkedList(DataType.STRING))),original);
        final Set<ArrayList<LinkedList<String>>> stored = pdc.get(key, DataType.asSet(DataType.asArrayList(DataType.asLinkedList(DataType.STRING))));
        assertEquals(original, stored);
    }

}
