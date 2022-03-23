/*
 * Copyright (c) 2022 Alexander Majka (mfnalex) / JEFF Media GbR
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of  MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program.  If not, see <http://www.gnu.org/licenses/>.
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
