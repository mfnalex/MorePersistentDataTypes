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
import org.bukkit.Material;
import org.junit.jupiter.api.Test;

import java.nio.file.StandardCopyOption;
import java.util.EnumMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnumTest extends UnitTest {

    @Test
    void testEnum() {
        final StandardCopyOption original = StandardCopyOption.ATOMIC_MOVE;
        pdc.set(key, DataType.asEnum(StandardCopyOption.class), original);
        assertEquals(original, pdc.get(key, DataType.asEnum(StandardCopyOption.class)));
    }

    @Test
    void testEnumMap() {
        pdc.set(key, DataType.asEnumMap(StandardCopyOption.class,DataType.STRING), TestData.ENUM_MAP);
        final EnumMap<StandardCopyOption, String> savedMap = pdc.get(key, DataType.asEnumMap(StandardCopyOption.class, DataType.STRING));
        assertEquals(TestData.ENUM_MAP,savedMap);
    }

    @Test
    void test() {
        EnumMap<Material,Integer> map = new EnumMap<>(Material.class);
        map.put(Material.DIRT, 1);
        map.put(Material.STONE, 2);
        map.put(Material.GRASS, 3);

        // Save the map
        pdc.set(key, DataType.asEnumMap(Material.class, DataType.INTEGER),map);

        // Load the map again
        EnumMap<Material,Integer> savedMap = pdc.get(key, DataType.asEnumMap(Material.class, DataType.INTEGER));
    }

    @Test
    void testEnumSet() {
        pdc.set(key, DataType.asEnumSet(StandardCopyOption.class), TestData.ENUM_SET);
        assertEquals(TestData.ENUM_SET, pdc.get(key, DataType.asEnumSet(StandardCopyOption.class)));
    }
}
