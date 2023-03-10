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

import com.jeff_media.morepersistentdatatypes.datatypes.BlockDataArrayDataType;
import com.jeff_media.morepersistentdatatypes.datatypes.BooleanArrayDataType;
import com.jeff_media.morepersistentdatatypes.datatypes.CharArrayDataType;
import com.jeff_media.morepersistentdatatypes.datatypes.DoubleArrayDataType;
import com.jeff_media.morepersistentdatatypes.datatypes.FileConfigurationDataType;
import com.jeff_media.morepersistentdatatypes.datatypes.FloatArrayDataType;
import com.jeff_media.morepersistentdatatypes.datatypes.GenericDataType;
import com.jeff_media.morepersistentdatatypes.datatypes.ShortArrayDataType;
import com.jeff_media.morepersistentdatatypes.datatypes.StringArrayDataType;
import com.jeff_media.morepersistentdatatypes.datatypes.UuidDataType;
import com.jeff_media.morepersistentdatatypes.datatypes.collections.ArrayDataType;
import com.jeff_media.morepersistentdatatypes.datatypes.collections.CollectionDataType;
import com.jeff_media.morepersistentdatatypes.datatypes.collections.MapDataType;
import com.jeff_media.morepersistentdatatypes.datatypes.serializable.ConfigurationSerializableArrayDataType;
import com.jeff_media.morepersistentdatatypes.datatypes.serializable.ConfigurationSerializableDataType;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.data.BlockData;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.util.BlockVector;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * Custom {@link PersistentDataType}s including Collections, Maps, and Arrays.
 *
 * All data types in this class will work in all Spigot versions since PDC was added (1.14.1+).
 *
 * @see DataType_1_18_1 for 1.18.1+ specific PersistentDataTypes
 */
@SuppressWarnings({"unchecked", "rawtypes", "unused"})
public interface DataType {

    /*
     * Custom PersistentDataTypes
     */
    //region Custom PersistentDataTypes
    /**
     * {@link PersistentDataType} for {@link AttributeModifier}s
     */
    PersistentDataType<byte[], AttributeModifier> ATTRIBUTE_MODIFIER = new ConfigurationSerializableDataType<>(AttributeModifier.class);
    /**
     * {@link PersistentDataType} for {@link AttributeModifier} arrays
     */
    PersistentDataType<byte[], AttributeModifier[]> ATTRIBUTE_MODIFIER_ARRAY = new ConfigurationSerializableArrayDataType<>(AttributeModifier[].class);
    /**
     * {@link PersistentDataType} for {@link BlockData}
     */
    PersistentDataType<String, BlockData> BLOCK_DATA = new GenericDataType<>(String.class, BlockData.class, Bukkit::createBlockData, BlockData::getAsString);
    /**
     * {@link PersistentDataType} for {@link BlockData} arrays
     */
    PersistentDataType<byte[], BlockData[]> BLOCK_DATA_ARRAY = new BlockDataArrayDataType();
    /**
     * {@link PersistentDataType} for {@link BlockVector}s
     */
    PersistentDataType<byte[], BlockVector> BLOCK_VECTOR = new ConfigurationSerializableDataType<>(BlockVector.class);
    /**
     * {@link PersistentDataType} for {@link BlockVector} arrays
     */
    PersistentDataType<byte[], BlockVector[]> BLOCK_VECTOR_ARRAY = new ConfigurationSerializableArrayDataType<>(BlockVector[].class);
    /**
     * {@link PersistentDataType} for {@link BoundingBox}es
     */
    PersistentDataType<byte[], BoundingBox> BOUNDING_BOX = new ConfigurationSerializableDataType<>(BoundingBox.class);
    /**
     * {@link PersistentDataType} for {@link BoundingBox} arrays
     */
    PersistentDataType<byte[], BoundingBox[]> BOUNDING_BOX_ARRAY = new ConfigurationSerializableArrayDataType<>(BoundingBox[].class);
    /**
     * {@link PersistentDataType} for {@link Color}s
     */
    PersistentDataType<byte[], Color> COLOR = new ConfigurationSerializableDataType<>(Color.class);
    /**
     * {@link PersistentDataType} for {@link Color} arrays
     */
    PersistentDataType<byte[], Color[]> COLOR_ARRAY = new ConfigurationSerializableArrayDataType<>(Color[].class);
    /**
     * {@link PersistentDataType} for {@link ConfigurationSerializable}s
     */
    PersistentDataType<byte[], ConfigurationSerializable> CONFIGURATION_SERIALIZABLE = new ConfigurationSerializableDataType<>(ConfigurationSerializable.class);
    /**
     * {@link PersistentDataType} for {@link ConfigurationSerializable} arrays
     */
    PersistentDataType<byte[], ConfigurationSerializable[]> CONFIGURATION_SERIALIZABLE_ARRAY = new ConfigurationSerializableArrayDataType<>(ConfigurationSerializable[].class);
    /**
     * {@link PersistentDataType} for {@link Date}s
     */
    PersistentDataType<Long, Date> DATE = new GenericDataType<>(Long.class, Date.class, Date::new, Date::getTime);
    /**
     * {@link PersistentDataType} for {@link FileConfiguration}s
     */
    PersistentDataType<String, FileConfiguration> FILE_CONFIGURATION = new FileConfigurationDataType();
    /**
     * {@link PersistentDataType} for {@link FireworkEffect}s
     */
    PersistentDataType<byte[], FireworkEffect> FIREWORK_EFFECT = new ConfigurationSerializableDataType<>(FireworkEffect.class);
    /**
     * {@link PersistentDataType} for {@link FireworkEffect} arrays
     */
    PersistentDataType<byte[], FireworkEffect[]> FIREWORK_EFFECT_ARRAY = new ConfigurationSerializableArrayDataType<>(FireworkEffect[].class);
    /**
     * {@link PersistentDataType} for {@link ItemMeta}s
     */
    PersistentDataType<byte[], ItemMeta> ITEM_META = new ConfigurationSerializableDataType<>(ItemMeta.class);
    /**
     * {@link PersistentDataType} for {@link ItemMeta} arrays
     */
    PersistentDataType<byte[], ItemMeta[]> ITEM_META_ARRAY = new ConfigurationSerializableArrayDataType<>(ItemMeta[].class);
    /**
     * {@link PersistentDataType} for {@link ItemStack}s
     */
    PersistentDataType<byte[], ItemStack> ITEM_STACK = new ConfigurationSerializableDataType<>(ItemStack.class);
    /**
     * {@link PersistentDataType} for {@link ItemStack} arrays
     */
    PersistentDataType<byte[], ItemStack[]> ITEM_STACK_ARRAY = new ConfigurationSerializableArrayDataType<>(ItemStack[].class);
    /**
     * {@link PersistentDataType} for {@link Location}s
     */
    PersistentDataType<byte[], Location> LOCATION = new ConfigurationSerializableDataType<>(Location.class);
    /**
     * {@link PersistentDataType} for {@link Location} arrays
     */
    PersistentDataType<byte[], Location[]> LOCATION_ARRAY = new ConfigurationSerializableArrayDataType<>(Location[].class);
    /**
     * {@link PersistentDataType} for {@link OfflinePlayer}s
     */
    PersistentDataType<byte[], OfflinePlayer> OFFLINE_PLAYER = new ConfigurationSerializableDataType<>(OfflinePlayer.class);
    /**
     * {@link PersistentDataType} for {@link OfflinePlayer} arrays
     */
    PersistentDataType<byte[], OfflinePlayer[]> OFFLINE_PLAYER_ARRAY = new ConfigurationSerializableArrayDataType<>(OfflinePlayer[].class);
    /**
     * {@link PersistentDataType} for Banner {@link Pattern}s
     */
    PersistentDataType<byte[], Pattern> PATTERN = new ConfigurationSerializableDataType<>(Pattern.class);
    /**
     * {@link PersistentDataType} for Banner {@link Pattern} arrays
     */
    PersistentDataType<byte[], Pattern[]> PATTERN_ARRAY = new ConfigurationSerializableArrayDataType<>(Pattern[].class);
    /**
     * {@link PersistentDataType} for {@link Player}s
     */
    PersistentDataType<byte[], Player> PLAYER = new ConfigurationSerializableDataType<>(Player.class);
    /**
     * {@link PersistentDataType} for {@link Player} arrays
     */
    PersistentDataType<byte[], Player[]> PLAYER_ARRAY = new ConfigurationSerializableArrayDataType<>(Player[].class);
    /**
     * {@link PersistentDataType} for {@link PotionEffect}s
     */
    PersistentDataType<byte[], PotionEffect> POTION_EFFECT = new ConfigurationSerializableDataType<>(PotionEffect.class);
    /**
     * {@link PersistentDataType} for {@link PotionEffect} arrays
     */
    PersistentDataType<byte[], PotionEffect[]> POTION_EFFECT_ARRAY = new ConfigurationSerializableArrayDataType<>(PotionEffect[].class);
    /**
     * {@link PersistentDataType} for {@link UUID}s
     */
    PersistentDataType<byte[], java.util.UUID> UUID = new UuidDataType();
    /**
     * {@link PersistentDataType} for {@link Vector}s
     */
    PersistentDataType<byte[], Vector> VECTOR = new ConfigurationSerializableDataType<>(Vector.class);
    /**
     * {@link PersistentDataType} for {@link Vector} arrays
     */
    PersistentDataType<byte[], Vector[]> VECTOR_ARRAY = new ConfigurationSerializableArrayDataType<>(Vector[].class);
    //endregion

    /*
     * Missing primitives and primitive arrays
     */
    //region Missing primitives and primitive arrays
    /**
     * {@link PersistentDataType} for {@link byte}s
     */
    PersistentDataType<Byte, Boolean> BOOLEAN = new GenericDataType<>(Byte.class, Boolean.class, aByte -> aByte == 1, aBoolean -> aBoolean ? (byte) 1 : (byte) 0);
    /**
     * {@link PersistentDataType} for {@link boolean} arrays
     */
    PersistentDataType<byte[], boolean[]> BOOLEAN_ARRAY = new BooleanArrayDataType();
    /**
     * {@link PersistentDataType} for {@link char}s
     */
    PersistentDataType<Integer, Character> CHARACTER = new GenericDataType<>(Integer.class, Character.class, integer -> (char) integer.intValue(), character -> (int) character);
    /**
     * {@link PersistentDataType} for {@link char} arrays
     */
    PersistentDataType<int[], char[]> CHARACTER_ARRAY = new CharArrayDataType();
    /**
     * {@link PersistentDataType} for {@link double} arrays
     */
    PersistentDataType<byte[], double[]> DOUBLE_ARRAY = new DoubleArrayDataType();
    /**
     * {@link PersistentDataType} for {@link float} arrays
     */
    PersistentDataType<byte[], float[]> FLOAT_ARRAY = new FloatArrayDataType();
    /**
     * {@link PersistentDataType} for {@link short} arrays
     */
    PersistentDataType<byte[], short[]> SHORT_ARRAY = new ShortArrayDataType();
    /**
     * {@link PersistentDataType} for {@link String} arrays
     */
    PersistentDataType<byte[], String[]> STRING_ARRAY = new StringArrayDataType(StandardCharsets.UTF_8);
    //endregion

    /*
     * Already existing PersistentDataTypes
     * I just put them here again for convenience
     */
    //region Already existing PersistentDataTypes
    /**
     * {@link PersistentDataType} for {@link byte}s
     */
    PersistentDataType<Byte, Byte> BYTE = PersistentDataType.BYTE;
    /**
     * {@link PersistentDataType} for {@link byte} arrays
     */
    PersistentDataType<byte[], byte[]> BYTE_ARRAY = PersistentDataType.BYTE_ARRAY;
    /**
     * {@link PersistentDataType} for {@link double}s
     */
    PersistentDataType<Double, Double> DOUBLE = PersistentDataType.DOUBLE;
    /**
     * {@link PersistentDataType} for {@link float}s
     */
    PersistentDataType<Float, Float> FLOAT = PersistentDataType.FLOAT;
    /**
     * {@link PersistentDataType} for {@link int}s
     */
    PersistentDataType<Integer, Integer> INTEGER = PersistentDataType.INTEGER;
    /**
     * {@link PersistentDataType} for {@link int} arrays
     */
    PersistentDataType<int[], int[]> INTEGER_ARRAY = PersistentDataType.INTEGER_ARRAY;
    /**
     * {@link PersistentDataType} for {@link long}s
     */
    PersistentDataType<Long, Long> LONG = PersistentDataType.LONG;
    /**
     * {@link PersistentDataType} for {@link long} arrays
     */
    PersistentDataType<long[], long[]> LONG_ARRAY = PersistentDataType.LONG_ARRAY;
    /**
     * {@link PersistentDataType} for {@link short}s
     */
    PersistentDataType<Short, Short> SHORT = PersistentDataType.SHORT;
    /**
     * {@link PersistentDataType} for {@link String}s
     */
    PersistentDataType<String, String> STRING = PersistentDataType.STRING;
    /**
     * {@link PersistentDataType} for {@link PersistentDataContainer}s
     */
    PersistentDataType<PersistentDataContainer, PersistentDataContainer> TAG_CONTAINER = PersistentDataType.TAG_CONTAINER;
    /**
     * {@link PersistentDataType} for {@link PersistentDataContainer} arrays
     */
    PersistentDataType<PersistentDataContainer[], PersistentDataContainer[]> TAG_CONTAINER_ARRAY = PersistentDataType.TAG_CONTAINER_ARRAY;
    //endregion

    /**
     * Creates a {@link PersistentDataType} for a given {@link Enum} class.
     *
     * @param enumClazz enum class to get a {@link PersistentDataType} for
     * @param <E>       enum type
     * @return a {@link PersistentDataType} for the given enum class
     */
    static <E extends Enum<E>> PersistentDataType<String, E> asEnum(final @NotNull Class<E> enumClazz) {
        return new GenericDataType<>(String.class, enumClazz, s -> Enum.valueOf(enumClazz, s), Enum::name);
    }

    /**
     * Turns an existing {@link PersistentDataType} into one that holds arrays of the same class. If a native array {@link PersistentDataType} already
     * exists, you should use that one instead of creating a generic one. Also note that the native array DataTypes
     * are not interchangeable with the generic ones created by this method.
     *
     * @param array    An (empty) array of the class
     * @param dataType The existing DataType
     * @param <T>      The type of the array
     * @return A {@link PersistentDataType} that holds arrays of the given class
     */
    static <T> ArrayDataType<T> asArray(final @NotNull T[] array,
                                        final @NotNull PersistentDataType<?, T> dataType) {
        return new ArrayDataType(array, dataType);
    }

    /**
     * Turns an existing {@link PersistentDataType} into one that holds a {@link Collection} of the same class
     * <p>
     * Example usage:
     * <pre>PersistentDataType&lt;?,CopyOnWriteArrayList&lt;String>> dataType = DataType.asGenericCollection(CopyOnWriteArrayList&lt;String>::new, DataType.STRING);</pre>
     *
     * @param supplier A {@link Supplier} that returns an empty instance of the given Collection class.
     * @param type     The existing DataType
     * @param <C>      The type of the collection
     * @param <T>      The type of the elements in the collection
     * @return A {@link PersistentDataType} holding a {@link Collection}
     */
    static <C extends Collection<T>, T> CollectionDataType<C, T> asGenericCollection(final @NotNull Supplier<C> supplier,
                                                                                     final @NotNull PersistentDataType<?, T> type) {
        return new CollectionDataType<>(supplier, type);
    }

    /**
     * Turns an existing {@link PersistentDataType} into one that holds a {@link List} of the same class.
     *
     * @param type The existing DataType
     * @param <T>  The type of the elements in the list
     * @return A {@link PersistentDataType} holding a {@link List}
     */
    static <T> CollectionDataType<List<T>, T> asList(final @NotNull PersistentDataType<?, T> type) {
        return asGenericCollection(ArrayList::new, type);
    }

    /**
     * Turns an existing {@link PersistentDataType} into one that holds an {@link ArrayList} of the same class
     *
     * @param type The existing DataType
     * @param <T>  The type of the elements in the list
     * @return A {@link PersistentDataType} holding an {@link ArrayList}
     */
    static <T> CollectionDataType<ArrayList<T>, T> asArrayList(final @NotNull PersistentDataType<?, T> type) {
        return asGenericCollection(ArrayList::new, type);
    }

    /**
     * Turns an existing {@link PersistentDataType} into one that holds a {@link LinkedList} of the same class
     *
     * @param type The existing DataType
     * @param <T>  The type of the elements in the list
     * @return A {@link PersistentDataType} holding a {@link LinkedList}
     */
    static <T> CollectionDataType<LinkedList<T>, T> asLinkedList(final @NotNull PersistentDataType<?, T> type) {
        return asGenericCollection(LinkedList::new, type);
    }

    /**
     * Turns an existing {@link PersistentDataType} into one that holds a {@link Set} of the same class
     *
     * @param type The existing DataType
     * @param <T>  The type of the elements in the set
     * @return A {@link PersistentDataType} holding a {@link Set}
     */
    static <T> CollectionDataType<Set<T>, T> asSet(final @NotNull PersistentDataType<?, T> type) {
        return asGenericCollection(HashSet::new, type);
    }

    /**
     * Turns an existing {@link PersistentDataType} into one that holds a {@link HashSet} of the same class
     *
     * @param type The existing DataType
     * @param <T>  The type of the elements in the set
     * @return A {@link PersistentDataType} holding a {@link HashSet}
     */
    static <T> CollectionDataType<HashSet<T>, T> asHashSet(final @NotNull PersistentDataType<?, T> type) {
        return asGenericCollection(HashSet::new, type);
    }

    /**
     * Turns an existing {@link PersistentDataType} into one that holds a {@link java.util.concurrent.CopyOnWriteArrayList} of the same class
     *
     * @param type The existing DataType
     * @param <T>  The type of the elements in the list
     * @return A {@link PersistentDataType} holding a {@link java.util.concurrent.CopyOnWriteArrayList}
     */
    static <T> CollectionDataType<CopyOnWriteArrayList<T>, T> asCopyOnWriteArrayList(final @NotNull PersistentDataType<?, T> type) {
        return asGenericCollection(CopyOnWriteArrayList::new, type);
    }

    /**
     * Turns an existing {@link PersistentDataType} into one that holds a {@link java.util.concurrent.CopyOnWriteArraySet} of the same class
     *
     * @param type The existing DataType
     * @param <T>  The type of the elements in the set
     * @return A {@link PersistentDataType} holding a {@link java.util.concurrent.CopyOnWriteArraySet}
     */
    static <T> CollectionDataType<CopyOnWriteArraySet<T>, T> asCopyOnWriteArraySet(final @NotNull PersistentDataType<?, T> type) {
        return asGenericCollection(CopyOnWriteArraySet::new, type);
    }

    /**
     * Returns a {@link PersistentDataType} holding an {@link EnumSet} of the given Enum class
     *
     * @param enumClazz The Enum class
     * @param <E>       The Enum type
     * @return A {@link PersistentDataType} holding an {@link EnumSet} of the given Enum class
     */
    static <E extends Enum<E>> CollectionDataType<EnumSet<E>, E> asEnumSet(final @NotNull Class<E> enumClazz) {
        return asGenericCollection(() -> EnumSet.noneOf(enumClazz), asEnum(enumClazz));
    }

    /**
     * Creates a {@link PersistentDataType} holding a specific {@link Map} implementation of the given DataTypes.
     * <p>
     * Example usage:
     * <pre>PersistentDataType&lt;?,Hashtable&lt;String,Integer>> dataType = DataType.asGenericMap(Hashtable&lt;String,Integer>::new, DataType.STRING, DataType.INTEGER);</pre>
     *
     * @param supplier  A {@link Supplier} that returns an empty instance of the desired Map class
     * @param keyType   The existing {@link PersistentDataType} for the map's keys
     * @param valueType The existing {@link PersistentDataType} for the map's values
     * @param <M>       Map type
     * @param <K>       Key type
     * @param <V>       Value type
     * @return PersistentDataType holding a Map
     */
    static <M extends Map<K, V>, K, V> MapDataType<M, K, V> asGenericMap(final @NotNull Supplier<M> supplier,
                                                                         final @NotNull PersistentDataType<?, K> keyType,
                                                                         final @NotNull PersistentDataType<?, V> valueType) {
        return new MapDataType<>(supplier, keyType, valueType);
    }

    /**
     * Creates a {@link PersistentDataType} holding a {@link Map} of the given DataTypes
     *
     * @param keyType   The existing {@link PersistentDataType} for the map's keys
     * @param valueType The existing {@link PersistentDataType} for the map's values
     * @param <K>       Key type
     * @param <V>       Value type
     * @return PersistentDataType holding a Map
     */
    static <K, V> MapDataType<Map<K, V>, K, V> asMap(final @NotNull PersistentDataType<?, K> keyType,
                                                     final @NotNull PersistentDataType<?, V> valueType) {
        return asGenericMap(HashMap::new, keyType, valueType);
    }

    /**
     * Creates a {@link PersistentDataType} holding a {@link HashMap} of the given DataTypes
     *
     * @param keyType   The existing {@link PersistentDataType} for the map's keys
     * @param valueType The existing {@link PersistentDataType} for the map's values
     * @param <K>       Key type
     * @param <V>       Value type
     * @return PersistentDataType holding a HashMap
     */
    static <K, V> MapDataType<HashMap<K, V>, K, V> asHashMap(final @NotNull PersistentDataType<?, K> keyType,
                                                             final @NotNull PersistentDataType<?, V> valueType) {
        return asGenericMap(HashMap::new, keyType, valueType);
    }

    /**
     * Creates a {@link PersistentDataType} holding a {@link java.util.concurrent.ConcurrentHashMap} of the given DataTypes
     *
     * @param keyType   The existing {@link PersistentDataType} for the map's keys
     * @param valueType The existing {@link PersistentDataType} for the map's values
     * @param <K>       Key type
     * @param <V>       Value type
     * @return PersistentDataType holding a ConcurrentHashMap
     */
    static <K, V> MapDataType<ConcurrentHashMap<K, V>, K, V> asConcurrentHashMap(final @NotNull PersistentDataType<?, K> keyType,
                                                                                 final @NotNull PersistentDataType<?, V> valueType) {
        return asGenericMap(ConcurrentHashMap::new, keyType, valueType);
    }

    /**
     * Creates a {@link PersistentDataType} holding a {@link IdentityHashMap} of the given DataTypes
     *
     * @param keyType   The existing {@link PersistentDataType} for the map's keys
     * @param valueType The existing {@link PersistentDataType} for the map's values
     * @param <K>       Key type
     * @param <V>       Value type
     * @return PersistentDataType holding a IdentityHashMap
     */
    static <K, V> MapDataType<IdentityHashMap<K, V>, K, V> asIdentityHashMap(final @NotNull PersistentDataType<?, K> keyType,
                                                                             final @NotNull PersistentDataType<?, V> valueType) {
        return asGenericMap(IdentityHashMap::new, keyType, valueType);
    }

    /**
     * Creates a {@link PersistentDataType} holding a {@link LinkedHashMap} of the given DataTypes
     *
     * @param keyType   The existing {@link PersistentDataType} for the map's keys
     * @param valueType The existing {@link PersistentDataType} for the map's values
     * @param <K>       Key type
     * @param <V>      Value type
     * @return PersistentDataType holding a LinkedHashMap
     */
    static <K, V> MapDataType<LinkedHashMap<K, V>, K, V> asLinkedHashMap(final @NotNull PersistentDataType<?, K> keyType,
                                                                         final @NotNull PersistentDataType<?, V> valueType) {
        return asGenericMap(LinkedHashMap::new, keyType, valueType);
    }

    /**
     * Creates a {@link PersistentDataType} holding a {@link TreeMap} of the given DataTypes
     *
     * @param keyType   The existing {@link PersistentDataType} for the map's keys
     * @param valueType The existing {@link PersistentDataType} for the map's values
     * @param <K>       Key type
     * @param <V>       Value type
     * @return PersistentDataType holding a TreeMap
     */
    static <K, V> MapDataType<TreeMap<K, V>, K, V> asTreeMap(final @NotNull PersistentDataType<?, K> keyType,
                                                             final @NotNull PersistentDataType<?, V> valueType) {
        return asGenericMap(TreeMap::new, keyType, valueType);
    }

    /**
     * Creates a {@link PersistentDataType} holding a {@link Hashtable} of the given DataTypes
     *
     * @param keyType   The existing {@link PersistentDataType} for the map's keys
     * @param valueType The existing {@link PersistentDataType} for the map's values
     * @param <K>       Key type
     * @param <V>       Value type
     * @return PersistentDataType holding a Hashtable
     */
    static <K, V> MapDataType<Hashtable<K, V>, K, V> asHashtable(final @NotNull PersistentDataType<?, K> keyType,
                                                                 final @NotNull PersistentDataType<?, V> valueType) {
        return asGenericMap(Hashtable::new, keyType, valueType);
    }

    /**
     * Creates a {@link PersistentDataType} holding an {@link EnumMap} of the given Enum Class and DataType
     *
     * @param enumClazz Enum class
     * @param valueType Existing {@link PersistentDataType} for the map's values
     * @param <E>       Enum type
     * @param <V>       Value type
     * @return PersistentDataType holding an EnumMap
     */
    static <E extends Enum<E>, V> MapDataType<EnumMap<E, V>, E, V> asEnumMap(final @NotNull Class<E> enumClazz,
                                                                             final @NotNull PersistentDataType<?, V> valueType) {
        return asGenericMap(() -> new EnumMap<>(enumClazz), asEnum(enumClazz), valueType);
    }

    /**
     * For internal use only.
     */
    class Utils {

        private Utils() {

        }

        private static final Map<String, NamespacedKey> KEY_KEYS = new HashMap<>();
        private static final Map<String, NamespacedKey> VALUE_KEYS = new HashMap<>();

        static {
            // Caching the first 100 keys. I think that's reasonable for most use cases
            IntStream.range(0, 100).forEach(number -> {
                getValueKey(number);
                getKeyKey(number);
            });
        }

        /**
         * Returns a NamespacedKey for the given key index.
         *
         * @param index The index of the key
         * @return The NamespacedKey
         */
        public static NamespacedKey getKeyKey(final int index) {
            return getKeyKey(String.valueOf(index));
        }

        /**
         * Returns a NamespacedKey for the given key name.
         *
         * @param name The name of the key
         * @return The NamespacedKey
         */
        public static NamespacedKey getKeyKey(final String name) {
            return KEY_KEYS.computeIfAbsent(name, __ -> NamespacedKey.fromString("k:" + name));
        }

        /**
         * Returns a NamespacedKey for the given value index.
         *
         * @param index The index of the value
         * @return The NamespacedKey
         */
        public static NamespacedKey getValueKey(final int index) {
            return getValueKey(String.valueOf(index));
        }

        /**
         * Returns a NamespacedKey for the given value name.
         *
         * @param name The name of the value
         * @return The NamespacedKey
         */
        public static NamespacedKey getValueKey(final String name) {
            return VALUE_KEYS.computeIfAbsent(name, __ -> NamespacedKey.fromString("v:" + name));
        }
    }

}
