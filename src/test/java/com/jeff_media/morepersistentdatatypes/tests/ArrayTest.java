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
import org.bukkit.attribute.AttributeModifier;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class ArrayTest extends UnitTest {

    @Test
    void testNativeShortArray() {
        pdc.set(key, DataType.SHORT_ARRAY,TestData.SHORT_PRIMITIVE_ARRAY);

        final short[] stored = pdc.get(key,DataType.SHORT_ARRAY);
        assertNotNull(stored);
        assertArrayEquals(TestData.SHORT_PRIMITIVE_ARRAY,stored);
    }

    @Test
    void testGenericUUIDArray() {
        pdc.set(key,DataType.asArray(TestData.UUID_ARRAY, DataType.UUID),TestData.UUID_ARRAY);

        final UUID[] storedArray = pdc.get(key, DataType.asArray(new UUID[0],DataType.UUID));
        assertArrayEquals(TestData.UUID_ARRAY,storedArray);

        final List<UUID> storedList = pdc.get(key,DataType.asArrayList(DataType.UUID));
        assertNotNull(storedList);
        assertArrayEquals(TestData.UUID_ARRAY,storedList.toArray());
    }

    @Test
    void testNativeArrayGenericArray() {
        final AttributeModifier[] original = new AttributeModifier[] { TestData.ATTRIBUTE_MODIFIER, TestData.ATTRIBUTE_MODIFIER };
        pdc.set(key,DataType.ATTRIBUTE_MODIFIER_ARRAY,original);
        pdc.set(key2,DataType.asArray(original, DataType.ATTRIBUTE_MODIFIER), original);
        assertArrayEquals(original, pdc.get(key,DataType.ATTRIBUTE_MODIFIER_ARRAY));
        assertArrayEquals(original, pdc.get(key2,DataType.asArray(new AttributeModifier[0],DataType.ATTRIBUTE_MODIFIER)));
    }
}
