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
import com.jeff_media.morepersistentdatatypes.TestData;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapTest extends UnitTest {

    @Test
    void testHashMapToMap() {
        pdc.set(key, DataType.asMap(DataType.STRING, DataType.INTEGER),TestData.HASH_MAP);
        final Map<String,Integer> savedMap = pdc.get(key, DataType.asMap(DataType.STRING, DataType.INTEGER));
        assertEquals(TestData.HASH_MAP,savedMap);
    }

    @Test
    void testHashMapToLinkedHashMap() {
        pdc.set(key, DataType.asHashMap(DataType.STRING, DataType.INTEGER), TestData.HASH_MAP);
        final LinkedHashMap<String, Integer> savedMap = pdc.get(key, DataType.asLinkedHashMap(DataType.STRING, DataType.INTEGER));
        assertEquals(TestData.HASH_MAP, savedMap);
    }
}
