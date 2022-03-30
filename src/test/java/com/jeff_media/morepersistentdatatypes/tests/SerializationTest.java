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
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.util.BlockVector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SerializationTest extends MorePersistentDataTypesUnitTest {

    @Test
    void testAttributeModifier() {
        pdc.set(key, DataType.ATTRIBUTE_MODIFIER, TestData.ATTRIBUTE_MODIFIER);
        assertEquals(TestData.ATTRIBUTE_MODIFIER, pdc.get(key, DataType.ATTRIBUTE_MODIFIER));
    }

    @Test
    void testBlockData() {
        final Block block = world.getBlockAt(0,64,0);
        block.setType(Material.CHEST);
        final BlockData original = block.getBlockData();
        pdc.set(key, DataType.BLOCK_DATA, original);
        final BlockData stored = pdc.get(key, DataType.BLOCK_DATA);
        assertNotNull(stored);
        assertEquals(original, stored);
        assertEquals(original.getAsString(), stored.getAsString());
        assertNotEquals(original.toString(), stored.toString());
    }

    @Test
    void testBlockVector() {
        final BlockVector original = new BlockVector(1,2,3);
        pdc.set(key, DataType.BLOCK_VECTOR, original);
        assertEquals(original, pdc.get(key, DataType.BLOCK_VECTOR));
    }
}
