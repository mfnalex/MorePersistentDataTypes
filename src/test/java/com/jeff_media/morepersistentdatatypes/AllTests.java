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

package com.jeff_media.morepersistentdatatypes;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.*;

import java.nio.file.StandardCopyOption;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AllTests {

    private static final Map<String,NamespacedKey> KEYS = new HashMap<>();
    @SuppressWarnings("FieldCanBeLocal")
    private ServerMock serverMock;
    private MockPlugin plugin;
    private Player player;

    @BeforeAll
    void setup() {
        serverMock = MockBukkit.mock();
        plugin = MockBukkit.load(MockPlugin.class);
        player = serverMock.addPlayer("mfnalex");
    }

    @AfterAll
    void tearDown() {
        MockBukkit.unmock();
    }

    @Test
    //@Order(1)
    void testEnumMap() {
        final NamespacedKey key = getKey("enumMap");
        final EnumMap<StandardCopyOption, String> map = new EnumMap<>(StandardCopyOption.class);
        map.put(StandardCopyOption.ATOMIC_MOVE, "atomic move");
        map.put(StandardCopyOption.REPLACE_EXISTING, "replace existing");
        map.put(StandardCopyOption.COPY_ATTRIBUTES, "copy attributes");
        player.getPersistentDataContainer().set(key, DataType.asEnumMap(StandardCopyOption.class,DataType.STRING),map);
        final EnumMap<StandardCopyOption, String> savedMap = player.getPersistentDataContainer().get(key, DataType.asEnumMap(StandardCopyOption.class, DataType.STRING));
        assert map.equals(savedMap);
    }

    @Test
    void testMap() {
        final NamespacedKey key = getKey("map");
        final Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("d", 4);
        map.put("c", 3);
        player.getPersistentDataContainer().set(key, DataType.asMap(DataType.STRING, DataType.INTEGER),map);
        final Map<String,Integer> savedMap = player.getPersistentDataContainer().get(key, DataType.asMap(DataType.STRING, DataType.INTEGER));
        assert map.equals(savedMap);
    }

//    @Test
//    void testItemStack() {
//        final NamespacedKey key = getKey("itemStack");
//        final ItemStack itemStack = new ItemStack(Material.DIAMOND_ORE);
//        player.getPersistentDataContainer().set(key, DataType.ITEM_STACK, itemStack);
//        final ItemStack savedItemStack = player.getPersistentDataContainer().get(key, DataType.ITEM_STACK);
//        assert itemStack.equals(savedItemStack);
//    }

    private NamespacedKey getKey(final String key) {
        return KEYS.computeIfAbsent(key, (__) -> new NamespacedKey(plugin, key));
    }

}