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
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CollectionTest extends UnitTest {

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
