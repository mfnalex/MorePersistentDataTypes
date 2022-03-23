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

import org.bukkit.NamespacedKey;

import java.util.HashMap;
import java.util.Map;

/**
 * For internal use only.
 */
public final class NamespacedKeyUtils {

    private static final Map<String, NamespacedKey> KEY_KEYS = new HashMap<>();
    private static final Map<String, NamespacedKey> VALUE_KEYS = new HashMap<>();

    public static NamespacedKey getKeyKey(final int name) {
        return getKeyKey(String.valueOf(name));
    }

    public static NamespacedKey getKeyKey(final String name) {
        return KEY_KEYS.computeIfAbsent(name, String -> NamespacedKey.fromString("k:" + name));
    }

    public static NamespacedKey getValueKey(final int name) {
        return getValueKey(String.valueOf(name));
    }

    public static NamespacedKey getValueKey(final String name) {
        return VALUE_KEYS.computeIfAbsent(name, String -> NamespacedKey.fromString("v:" + name));
    }
}
