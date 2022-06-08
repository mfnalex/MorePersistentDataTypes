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

package com.jeff_media.morepersistentdatatypes;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MorePersistentDataTypesUnitTest {

    private static final Map<String,NamespacedKey> KEYS = new HashMap<>();
    protected final ServerMock serverMock = MockBukkit.mock();
    protected World world;
    protected NamespacedKey key, key2;
    protected MorePersistentDataTypesMockPlugin plugin;
    protected Player player;
    protected PersistentDataContainer pdc;

    @BeforeAll
    void setup() {
        plugin = MockBukkit.load(MorePersistentDataTypesMockPlugin.class);
        world = serverMock.addSimpleWorld("test_world");
        key = getKey("test");
        key2 = getKey("test2");
        player = serverMock.addPlayer("mfnalex");
        pdc = player.getPersistentDataContainer();
    }

    @BeforeEach
    void clearPdc() {
        final Set<NamespacedKey> keys = new HashSet<>(pdc.getKeys());
        keys.forEach(key -> pdc.remove(key));
    }

    @AfterAll
    void tearDown() {
        MockBukkit.unmock();
    }


    protected NamespacedKey getKey(final String key) {
        return KEYS.computeIfAbsent(key, (__) -> new NamespacedKey(plugin, key));
    }

}