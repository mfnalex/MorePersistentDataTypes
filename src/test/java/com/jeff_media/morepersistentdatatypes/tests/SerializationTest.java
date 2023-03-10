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

import be.seeseemelk.mockbukkit.UnimplementedOperationException;
import com.jeff_media.morepersistentdatatypes.DataType;
import com.jeff_media.morepersistentdatatypes.UnitTest;
import com.jeff_media.morepersistentdatatypes.TestData;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.util.BlockVector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SerializationTest extends UnitTest {

    @Test
    void testAttributeModifier() {
        pdc.set(key, DataType.ATTRIBUTE_MODIFIER, TestData.ATTRIBUTE_MODIFIER);
        assertEquals(TestData.ATTRIBUTE_MODIFIER, pdc.get(key, DataType.ATTRIBUTE_MODIFIER));
    }

    @Test
    void testBlockData() {
        try {
            final Block block = world.getBlockAt(0, 64, 0);
            block.setType(Material.CHEST);
            final BlockData original = block.getBlockData();
            pdc.set(key, DataType.BLOCK_DATA, original);
            final BlockData stored = pdc.get(key, DataType.BLOCK_DATA);
            assertNotNull(stored);
            assertEquals(original, stored);
            assertEquals(original.getAsString(), stored.getAsString());
            assertNotEquals(original.toString(), stored.toString());
        } catch (UnimplementedOperationException ignored) {
            // Ignore
        }
    }

    @Test
    void testBlockVector() {
        final BlockVector original = new BlockVector(1,2,3);
        pdc.set(key, DataType.BLOCK_VECTOR, original);
        assertEquals(original, pdc.get(key, DataType.BLOCK_VECTOR));
    }
}
