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
