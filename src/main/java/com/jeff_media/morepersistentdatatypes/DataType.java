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

import com.jeff_media.morepersistentdatatypes.datatypes.*;
import com.jeff_media.morepersistentdatatypes.datatypes.collections.ArrayDataType;
import com.jeff_media.morepersistentdatatypes.datatypes.collections.CollectionDataType;
import com.jeff_media.morepersistentdatatypes.datatypes.collections.MapDataType;
import com.jeff_media.morepersistentdatatypes.datatypes.serializable.ConfigurationSerializableArrayDataType;
import com.jeff_media.morepersistentdatatypes.datatypes.serializable.ConfigurationSerializableDataType;
import org.bukkit.*;
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
import org.bukkit.util.BlockVector;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Supplier;

/**
 * Custom {@link PersistentDataType}s including Collections, Maps, and Arrays.
 * <p>
 * <b>MorePersistentDataTypes</b> adds a ton of custom {@link org.bukkit.persistence.PersistentDataType}s that can be used
 * in conjunction with Bukkit's {@link org.bukkit.persistence.PersistentDataContainer}.
 *
 * <p>
 * It also allows to use <b>any kind of {@link java.util.Collection}, {@link java.util.Map} or Array.</b> You can easily
 * convert any given PersistentDataType to hold a collection, map or array instead. For example:
 * <pre>PersistentDataContainer pdc = player.getPersistentDataContainer();
 * NamespacedKey key = new NamespacedKey(myPlugin,"backpack-contents");
 * List&lt;ItemStack&gt; map = pdc.get("backpack-contents",DataType.asList(DataType.ITEM_STACK));</pre>
 *
 * <p>
 * All the collection datatypes can of course be nested infinitely, for example:
 * <pre>
 * Map&lt;String,Map&lt;Material,Integer&gt;&gt; map = pdc.get(namespacedKey,
 *                  DataType.asMap(DataType.STRING, DataType.asMap(DataType.asEnum(Material.class),DataType.INTEGER)));
 * </pre>
 *
 * <p>
 * Data saved as {@link Collection} can also be read as Array and vice versa. All {@link Collection}s are interchangeable. This means you
 * can save a {@link HashMap<String>} in a {@link PersistentDataContainer} and then later retrieve it as Array, or as
 * {@link Set<String>}.
 * <p>
 * {@link Map}s are also interchangeable - you can save a {@link Map} and then later retrieve it as {@link
 * LinkedHashMap}.
 * <p>
 * {@link Map}s are not interchangeable with {@link Collections} or Arrays, though.
 */
@SuppressWarnings({"unchecked", "rawtypes", "unused"})
public interface DataType {

    /*
     * Custom PersistentDataTypes
     */
    //region Custom PersistentDataTypes
    /**
     * DataType for {@link AttributeModifier}s
     */
    PersistentDataType<byte[], AttributeModifier> ATTRIBUTE_MODIFIER = new ConfigurationSerializableDataType<>(AttributeModifier.class);
    /**
     * DataType for {@link AttributeModifier} arrays
     */
    PersistentDataType<byte[], AttributeModifier[]> ATTRIBUTE_MODIFIER_ARRAY = new ConfigurationSerializableArrayDataType<>(AttributeModifier[].class);
    /**
     * DataType for {@link BlockData}
     */
    PersistentDataType<String, BlockData> BLOCK_DATA = new GenericDataType<>(String.class, BlockData.class, Bukkit::createBlockData, BlockData::getAsString);
    /**
     * DataType for {@link BlockData} arrays
     */
    PersistentDataType<byte[], BlockData[]> BLOCK_DATA_ARRAY = new BlockDataArrayDataType();
    /**
     * DataType for {@link BlockVector}s
     */
    PersistentDataType<byte[], BlockVector> BLOCK_VECTOR = new ConfigurationSerializableDataType<>(BlockVector.class);
    /**
     * DataType for {@link BlockVector} arrays
     */
    PersistentDataType<byte[], BlockVector[]> BLOCK_VECTOR_ARRAY = new ConfigurationSerializableArrayDataType<>(BlockVector[].class);
    /**
     * DataType for {@link BoundingBox}es
     */
    PersistentDataType<byte[], BoundingBox> BOUNDING_BOX = new ConfigurationSerializableDataType<>(BoundingBox.class);
    /**
     * DataType for {@link BoundingBox} arrays
     */
    PersistentDataType<byte[], BoundingBox[]> BOUNDING_BOX_ARRAY = new ConfigurationSerializableArrayDataType<>(BoundingBox[].class);
    /**
     * DataType for {@link Color}s
     */
    PersistentDataType<byte[], Color> COLOR = new ConfigurationSerializableDataType<>(Color.class);
    /**
     * DataType for {@link Color} arrays
     */
    PersistentDataType<byte[], Color[]> COLOR_ARRAY = new ConfigurationSerializableArrayDataType<>(Color[].class);
    /**
     * DataType for {@link ConfigurationSerializable}s
     */
    PersistentDataType<byte[], ConfigurationSerializable> CONFIGURATION_SERIALIZABLE = new ConfigurationSerializableDataType<>(ConfigurationSerializable.class);
    /**
     * DataType for {@link ConfigurationSerializable} arrays
     */
    PersistentDataType<byte[], ConfigurationSerializable[]> CONFIGURATION_SERIALIZABLE_ARRAY = new ConfigurationSerializableArrayDataType<>(ConfigurationSerializable[].class);
    /**
     * DataType for {@link Date}s
     */
    PersistentDataType<Long, Date> DATE = new GenericDataType<>(Long.class, Date.class, Date::new, Date::getTime);
    /**
     * DataType for {@link FileConfiguration}s
     */
    PersistentDataType<String, FileConfiguration> FILE_CONFIGURATION = new FileConfigurationDataType();
    /**
     * DataType for {@link FireworkEffect}s
     */
    PersistentDataType<byte[], FireworkEffect> FIREWORK_EFFECT = new ConfigurationSerializableDataType<>(FireworkEffect.class);
    /**
     * DataType for {@link FireworkEffect} arrays
     */
    PersistentDataType<byte[], FireworkEffect[]> FIREWORK_EFFECT_ARRAY = new ConfigurationSerializableArrayDataType<>(FireworkEffect[].class);
    /**
     * DataType for {@link ItemMeta}s
     */
    PersistentDataType<byte[], ItemMeta> ITEM_META = new ConfigurationSerializableDataType<>(ItemMeta.class);
    /**
     * DataType for {@link ItemMeta} arrays
     */
    PersistentDataType<byte[], ItemMeta[]> ITEM_META_ARRAY = new ConfigurationSerializableArrayDataType<>(ItemMeta[].class);
    /**
     * DataType for {@link ItemStack}s
     */
    PersistentDataType<byte[], ItemStack> ITEM_STACK = new ConfigurationSerializableDataType<>(ItemStack.class);
    /**
     * DataType for {@link ItemStack} arrays
     */
    PersistentDataType<byte[], ItemStack[]> ITEM_STACK_ARRAY = new ConfigurationSerializableArrayDataType<>(ItemStack[].class);
    /**
     * DataType for {@link Location}s
     */
    PersistentDataType<byte[], Location> LOCATION = new ConfigurationSerializableDataType<>(Location.class);
    /**
     * DataType for {@link Location} arrays
     */
    PersistentDataType<byte[], Location[]> LOCATION_ARRAY = new ConfigurationSerializableArrayDataType<>(Location[].class);
    /**
     * DataType for {@link OfflinePlayer}s
     */
    PersistentDataType<byte[], OfflinePlayer> OFFLINE_PLAYER = new ConfigurationSerializableDataType<>(OfflinePlayer.class);
    /**
     * DataType for {@link OfflinePlayer} arrays
     */
    PersistentDataType<byte[], OfflinePlayer[]> OFFLINE_PLAYER_ARRAY = new ConfigurationSerializableArrayDataType<>(OfflinePlayer[].class);
    /**
     * DataType for Banner {@link Pattern}s
     */
    PersistentDataType<byte[], Pattern> PATTERN = new ConfigurationSerializableDataType<>(Pattern.class);
    /**
     * DataType for Banner {@link Pattern} arrays
     */
    PersistentDataType<byte[], Pattern[]> PATTERN_ARRAY = new ConfigurationSerializableArrayDataType<>(Pattern[].class);
    /**
     * DataType for {@link Player}s
     */
    PersistentDataType<byte[], Player> PLAYER = new ConfigurationSerializableDataType<>(Player.class);
    /**
     * DataType for {@link Player} arrays
     */
    PersistentDataType<byte[], Player[]> PLAYER_ARRAY = new ConfigurationSerializableArrayDataType<>(Player[].class);
    /**
     * DataType for {@link PotionEffect}s
     */
    PersistentDataType<byte[], PotionEffect> POTION_EFFECT = new ConfigurationSerializableDataType<>(PotionEffect.class);
    /**
     * DataType for {@link PotionEffect} arrays
     */
    PersistentDataType<byte[], PotionEffect[]> POTION_EFFECT_ARRAY = new ConfigurationSerializableArrayDataType<>(PotionEffect[].class);
    /**
     * DataType for {@link UUID}s
     */
    PersistentDataType<byte[], java.util.UUID> UUID = new UuidDataType();
    /**
     * DataType for {@link Vector}s
     */
    PersistentDataType<byte[], Vector> VECTOR = new ConfigurationSerializableDataType<>(Vector.class);
    /**
     * DataType for {@link Vector} arrays
     */
    PersistentDataType<byte[], Vector[]> VECTOR_ARRAY = new ConfigurationSerializableArrayDataType<>(Vector[].class);
    //endregion

    /*
     * Missing primitives and primitive arrays
     */
    //region Missing primitives and primitive arrays
    /**
     * DataType for @{link byte}s
     */
    PersistentDataType<Byte, Boolean> BOOLEAN = new GenericDataType<>(Byte.class, Boolean.class, aByte -> aByte == 1, aBoolean -> aBoolean ? (byte) 1 : (byte) 0);
    /**
     * DataType for @{link boolean} arrays
     */
    PersistentDataType<byte[], boolean[]> BOOLEAN_ARRAY = new BooleanArrayDataType();
    /**
     * DataType for @{link char}s
     */
    PersistentDataType<Integer, Character> CHARACTER = new GenericDataType<>(Integer.class, Character.class, integer -> (char) integer.intValue(), character -> (int) character);
    /**
     * DataType for @{link char} arrays
     */
    PersistentDataType<int[], char[]> CHARACTER_ARRAY = new CharArrayDataType();
    /**
     * DataType for @{link double} arrays
     */
    PersistentDataType<byte[], double[]> DOUBLE_ARRAY = new DoubleArrayDataType();
    /**
     * DataType for @{link float} arrays
     */
    PersistentDataType<byte[], float[]> FLOAT_ARRAY = new FloatArrayDataType();
    /**
     * DataType for {@link short} arrays
     */
    PersistentDataType<byte[], short[]> SHORT_ARRAY = new ShortArrayDataType();
    /**
     * DataType for {@link String} arrays
     */
    PersistentDataType<byte[], String[]> STRING_ARRAY = new StringArrayDataType(StandardCharsets.UTF_8);
    //endregion

    /*
     * Already existing PersistentDataTypes
     * I just put them here again for convenience
     */
    //region Already existing PersistentDataTypes
    /**
     * DataType for {@link byte}s
     */
    PersistentDataType<Byte, Byte> BYTE = PersistentDataType.BYTE;
    /**
     * DataType for {@link byte} arrays
     */
    PersistentDataType<byte[], byte[]> BYTE_ARRAY = PersistentDataType.BYTE_ARRAY;
    /**
     * DataType for {@link double}s
     */
    PersistentDataType<Double, Double> DOUBLE = PersistentDataType.DOUBLE;
    /**
     * DataType for {@link float}s
     */
    PersistentDataType<Float, Float> FLOAT = PersistentDataType.FLOAT;
    /**
     * DataType for {@link int}s
     */
    PersistentDataType<Integer, Integer> INTEGER = PersistentDataType.INTEGER;
    /**
     * DataType for {@link int} arrays
     */
    PersistentDataType<int[], int[]> INTEGER_ARRAY = PersistentDataType.INTEGER_ARRAY;
    /**
     * DataType for {@link long}s
     */
    PersistentDataType<Long, Long> LONG = PersistentDataType.LONG;
    /**
     * DataType for {@link long} arrays
     */
    PersistentDataType<long[], long[]> LONG_ARRAY = PersistentDataType.LONG_ARRAY;
    /**
     * DataType for {@link short}s
     */
    PersistentDataType<Short, Short> SHORT = PersistentDataType.SHORT;
    /**
     * DataType for {@link String}s
     */
    PersistentDataType<String, String> STRING = PersistentDataType.STRING;
    /**
     * DataType for {@link PersistentDataContainer}s
     */
    PersistentDataType<PersistentDataContainer, PersistentDataContainer> TAG_CONTAINER = PersistentDataType.TAG_CONTAINER;
    /**
     * DataType for {@link PersistentDataContainer} arrays
     */
    PersistentDataType<PersistentDataContainer[], PersistentDataContainer[]> TAG_CONTAINER_ARRAY = PersistentDataType.TAG_CONTAINER_ARRAY;
    //endregion

    /**
     * Creates a DataType for a given {@link Enum} class.
     * @param enumClazz enum class to get a DataType for
     */
    static <E extends Enum<E>> PersistentDataType<String, E> asEnum(final @NotNull Class<E> enumClazz) {
        return new GenericDataType<>(String.class, enumClazz, s -> Enum.valueOf(enumClazz, s), Enum::name);
    }

    /**
     * Turns an existing DataType into one that holds arrays of the same class. If a native array DataType already
     * exists, you should use that one instead of creating a generic one. Also note that the native array DataTypes
     * are not interchangeable with the generic ones created by this method.
     * @param array An (empty) array of the class
     * @param dataType The existing DataType
     */
    static <D> ArrayDataType<D> asArray(final @NotNull D[] array,
                                        final @NotNull PersistentDataType<?, D> dataType) {
        return new ArrayDataType(array, dataType);
    }

    /**
     * Turns an existing DataType into one that holds a {@link Collection} of the same class
     * <p>
     * Example usage:
     * <pre>PersistentDataType&lt;?,CopyOnWriteArrayList&lt;String>> dataType = DataType.asGenericCollection(CopyOnWriteArrayList&lt;String>::new, DataType.STRING);</pre>
     * @param collectionSupplier A {@link Supplier} that returns an empty instance of the given Collection class.
     * @param type The existing DataType
     */
    static <C extends Collection<D>, D> CollectionDataType<C,D> asGenericCollection(final @NotNull Supplier<C> collectionSupplier,
                                                                                    final @NotNull PersistentDataType<?, D> type) {
        return new CollectionDataType<>(collectionSupplier, type);
    }

    /**
     * Turns an existing DataType into one that holds a {@link List} of the same class.
     * @param type The existing DataType
     */
    static <D> CollectionDataType<List<D>,D> asList(final @NotNull PersistentDataType<?, D> type) {
        return asGenericCollection(ArrayList::new, type);
    }

    /**
     * Turns an existing DataType into one that holds an {@link ArrayList} of the same class
     * @param type The existing DataType
     */
    static <D> CollectionDataType<ArrayList<D>,D> asArrayList(final @NotNull PersistentDataType<?, D> type) {
        return asGenericCollection(ArrayList::new, type);
    }

    /**
     * Turns an existing DataType into one that holds a {@link LinkedList} of the same class
     * @param type The existing DataType
     */
    static <D> CollectionDataType<LinkedList<D>,D> asLinkedList(final @NotNull PersistentDataType<?, D> type) {
        return asGenericCollection(LinkedList::new, type);
    }

    /**
     * Turns an existing DataType into one that holds a {@link Set} of the same class
     * @param type The existing DataType
     */
    static <D> CollectionDataType<Set<D>,D> asSet(final @NotNull PersistentDataType<?, D> type) {
        return asGenericCollection(HashSet::new, type);
    }

    /**
     * Turns an existing DataType into one that holds a {@link HashSet} of the same class
     * @param type The existing DataType
     */
    static <D> CollectionDataType<HashSet<D>,D> asHashSet(final @NotNull PersistentDataType<?, D> type) {
        return asGenericCollection(HashSet::new, type);
    }

    /**
     * Turns an existing DataType into one that holds a {@link java.util.concurrent.CopyOnWriteArrayList} of the same class
     * @param type The existing DataType
     */
    static <D> CollectionDataType<CopyOnWriteArrayList<D>,D> asCopyOnWriteArrayList(final @NotNull PersistentDataType<?, D> type) {
        return asGenericCollection(CopyOnWriteArrayList::new, type);
    }

    /**
     * Turns an existing DataType into one that holds a {@link java.util.concurrent.CopyOnWriteArraySet} of the same class
     * @param type The existing DataType
     */
    static <D> CollectionDataType<CopyOnWriteArraySet<D>,D> asCopyOnWriteArraySet(final @NotNull PersistentDataType<?, D> type) {
        return asGenericCollection(CopyOnWriteArraySet::new, type);
    }

    /**
     * Creates a DataType holding a specific {@link Map} implementation of the given DataTypes.
     * <p>
     * Example usage:
     * <pre>PersistentDataType&lt;?,Hashtable&lt;String,Integer>> dataType = DataType.asGenericMap(Hashtable&lt;String,Integer>::new, DataType.STRING, DataType.INTEGER);</pre>
     * @param mapSupplier A {@link Supplier} that returns an empty instance of the desired Map class
     * @param keyType The existing DataType for the map's keys
     * @param valueType The existing DataType for the map's values
     */
    static <M extends Map<K, V>, K, V> MapDataType<M, K, V> asGenericMap(final @NotNull Supplier<M> mapSupplier,
                                                                        final @NotNull PersistentDataType<?, K> keyType,
                                                                        final @NotNull PersistentDataType<?, V> valueType) {
        return new MapDataType<>(mapSupplier, keyType, valueType);
    }

    /**
     * Creates a DataType holding a {@link Map} of the given DataTypes
     * @param keyType The existing DataType for the map's keys
     * @param valueType The existing DataType for the map's values
     */
    static <K, V> MapDataType<Map<K,V>,K, V> asMap(final @NotNull PersistentDataType<?, K> keyType,
                                                             final @NotNull PersistentDataType<?, V> valueType) {
        return asGenericMap(HashMap::new, keyType, valueType);
    }

    /**
     * Creates a DataType holding a {@link HashMap} of the given DataTypes
     * @param keyType The existing DataType for the map's keys
     * @param valueType The existing DataType for the map's values
     */
    static <K, V> MapDataType<HashMap<K,V>,K,V> asHashMap(final @NotNull PersistentDataType<?, K> keyType,
                                                          final @NotNull PersistentDataType<?, V> valueType) {
        return asGenericMap(HashMap::new, keyType, valueType);
    }

    /**
     * Creates a DataType holding a {@link java.util.concurrent.ConcurrentHashMap} of the given DataTypes
     * @param keyType The existing DataType for the map's keys
     * @param valueType The existing DataType for the map's values
     */
    static <K, V> MapDataType<ConcurrentHashMap<K,V>,K,V> asConcurrentHashMap(final @NotNull PersistentDataType<?, K> keyType,
                                                                              final @NotNull PersistentDataType<?, V> valueType) {
        return asGenericMap(ConcurrentHashMap::new, keyType, valueType);
    }

    /**
     * Creates a DataType holding a {@link IdentityHashMap} of the given DataTypes
     * @param keyType The existing DataType for the map's keys
     * @param valueType The existing DataType for the map's values
     */
    static <K, V> MapDataType<IdentityHashMap<K,V>,K,V> asIdentityHashMap(final @NotNull PersistentDataType<?, K> keyType,
                                                                          final @NotNull PersistentDataType<?, V> valueType) {
        return asGenericMap(IdentityHashMap::new, keyType, valueType);
    }

    /**
     * Creates a DataType holding a {@link LinkedHashMap} of the given DataTypes
     * @param keyType The existing DataType for the map's keys
     * @param valueType The existing DataType for the map's values
     */
    static <K, V> MapDataType<LinkedHashMap<K,V>,K,V> asLinkedHashMap(final @NotNull PersistentDataType<?, K> keyType,
                                                                      final @NotNull PersistentDataType<?, V> valueType) {
        return asGenericMap(LinkedHashMap::new, keyType, valueType);
    }

    /**
     * Creates a DataType holding a {@link TreeMap} of the given DataTypes
     * @param keyType The existing DataType for the map's keys
     * @param valueType The existing DataType for the map's values
     */
    static <K, V> MapDataType<TreeMap<K,V>,K,V> asTreeMap(final @NotNull PersistentDataType<?, K> keyType,
                                                          final @NotNull PersistentDataType<?, V> valueType) {
        return asGenericMap(TreeMap::new, keyType, valueType);
    }

    /**
     * Creates a DataType holding a {@link Hashtable} of the given DataTypes
     * @param keyType The existing DataType for the map's keys
     * @param valueType The existing DataType for the map's values
     */
    static <K, V> MapDataType<Hashtable<K,V>,K,V> asHashtable(final @NotNull PersistentDataType<?, K> keyType,
                                                              final @NotNull PersistentDataType<?, V> valueType) {
        return asGenericMap(Hashtable::new, keyType, valueType);
    }

    /**
     * Creates a DataType holding an {@link EnumMap} of the given Enum Class and DataType
     * @param enumClazz Enum class
     * @param valueType Existing DataType for the map's values
     */
    static <K extends Enum<K>,V> MapDataType<EnumMap<K,V>,K,V> asEnumMap(final @NotNull Class<K> enumClazz,
                                                                         final @NotNull PersistentDataType<?,V> valueType) {
        return asGenericMap(() -> new EnumMap<>(enumClazz), asEnum(enumClazz), valueType);
    }

}
