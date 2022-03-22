package com.jeff_media.morepersistentdatatypes;

import com.jeff_media.morepersistentdatatypes.implementation.*;
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

import java.nio.charset.StandardCharsets;
import java.util.*;

public interface DataType {

    /*
    Custom types
     */
    PersistentDataType<byte[], AttributeModifier> ATTRIBUTE_MODIFIER = new ConfigurationSerializableDataType<>(AttributeModifier.class);
    PersistentDataType<byte[], AttributeModifier[]> ATTRIBUTE_MODIFIER_ARRAY = new ConfigurationSerializableArrayDataType<>(AttributeModifier.class, AttributeModifier[].class);
    PersistentDataType<String, BlockData> BLOCK_DATA = new GenericDataType<>(String.class, BlockData.class, Bukkit::createBlockData, BlockData::getAsString);
    PersistentDataType<byte[], BlockVector> BLOCK_VECTOR = new ConfigurationSerializableDataType<>(BlockVector.class);
    PersistentDataType<byte[], BlockVector[]> BLOCK_VECTOR_ARRAY = new ConfigurationSerializableArrayDataType<>(BlockVector.class, BlockVector[].class);
    PersistentDataType<byte[], BoundingBox> BOUNDING_BOX = new ConfigurationSerializableDataType<>(BoundingBox.class);
    PersistentDataType<byte[], BoundingBox[]> BOUNDING_BOX_ARRAY = new ConfigurationSerializableArrayDataType<>(BoundingBox.class, BoundingBox[].class);
    PersistentDataType<byte[], Color> COLOR = new ConfigurationSerializableDataType<>(Color.class);
    PersistentDataType<byte[], Color[]> COLOR_ARRAY = new ConfigurationSerializableArrayDataType<>(Color.class, Color[].class);
    PersistentDataType<byte[], ConfigurationSerializable> CONFIGURATION_SERIALIZABLE = new ConfigurationSerializableDataType<>(ConfigurationSerializable.class);
    PersistentDataType<byte[], ConfigurationSerializable[]> CONFIGURATION_SERIALIZABLE_ARRAY = new ConfigurationSerializableArrayDataType<>(ConfigurationSerializable.class, ConfigurationSerializable[].class);
    PersistentDataType<Long, Date> DATE = new GenericDataType<>(Long.class, Date.class, Date::new, Date::getTime);
    PersistentDataType<byte[], FireworkEffect> FIREWORK_EFFECT = new ConfigurationSerializableDataType<>(FireworkEffect.class);
    PersistentDataType<byte[], FireworkEffect[]> FIREWORK_EFFECT_ARRAY = new ConfigurationSerializableArrayDataType<>(FireworkEffect.class, FireworkEffect[].class);
    PersistentDataType<byte[], ItemMeta> ITEM_META = new ConfigurationSerializableDataType<>(ItemMeta.class);
    PersistentDataType<byte[], ItemMeta[]> ITEM_META_ARRAY = new ConfigurationSerializableArrayDataType<>(ItemMeta.class, ItemMeta[].class);
    PersistentDataType<byte[], ItemStack> ITEM_STACK = new ConfigurationSerializableDataType<>(ItemStack.class);
    PersistentDataType<byte[], ItemStack[]> ITEM_STACK_ARRAY = new ConfigurationSerializableArrayDataType<>(ItemStack.class, ItemStack[].class);
    PersistentDataType<byte[], Location> LOCATION = new ConfigurationSerializableDataType<>(Location.class);
    PersistentDataType<byte[], Location[]> LOCATION_ARRAY = new ConfigurationSerializableArrayDataType<>(Location.class, Location[].class);
    PersistentDataType<byte[], OfflinePlayer> OFFLINE_PLAYER = new ConfigurationSerializableDataType<>(OfflinePlayer.class);
    PersistentDataType<byte[], OfflinePlayer[]> OFFLINE_PLAYER_ARRAY = new ConfigurationSerializableArrayDataType<>(OfflinePlayer.class, OfflinePlayer[].class);
    PersistentDataType<byte[], Pattern> PATTERN = new ConfigurationSerializableDataType<>(Pattern.class);
    PersistentDataType<byte[], Pattern[]> PATTERN_ARRAY = new ConfigurationSerializableArrayDataType<>(Pattern.class, Pattern[].class);
    PersistentDataType<byte[], Player> PLAYER = new ConfigurationSerializableDataType<>(Player.class);
    PersistentDataType<byte[], Player[]> PLAYER_ARRAY = new ConfigurationSerializableArrayDataType<>(Player.class, Player[].class);
    PersistentDataType<byte[], PotionEffect> POTION_EFFECT = new ConfigurationSerializableDataType<>(PotionEffect.class);
    PersistentDataType<byte[], PotionEffect[]> POTION_EFFECT_ARRAY = new ConfigurationSerializableArrayDataType<>(PotionEffect.class, PotionEffect[].class);
    PersistentDataType<byte[], String[]> STRING_ARRAY = new StringArrayDataType(StandardCharsets.UTF_8);
    PersistentDataType<byte[], java.util.UUID> UUID = new UuidDataType();
    PersistentDataType<byte[], Vector> VECTOR = new ConfigurationSerializableDataType<>(Vector.class);
    PersistentDataType<byte[], Vector[]> VECTOR_ARRAY = new ConfigurationSerializableArrayDataType<>(Vector.class, Vector[].class);
    PersistentDataType<String, FileConfiguration> FILE_CONFIGURATION = new FileConfigurationDataType();

    /*
    Missing primitives and primitive arrays
    */
    PersistentDataType<Byte, Boolean> BOOLEAN = new GenericDataType<>(Byte.class, Boolean.class, aByte -> aByte == 1, aBoolean -> aBoolean ? (byte) 1: (byte) 0);
    PersistentDataType<byte[], boolean[]> BOOLEAN_ARRAY = new BooleanArrayDataType();
    PersistentDataType<Integer, Character> CHARACTER = new GenericDataType<>(Integer.class, Character.class, integer -> (char) integer.intValue(), character -> (int) character);
    PersistentDataType<int[], char[]> CHARACTER_ARRAY = new CharArrayDataType();
    PersistentDataType<byte[], double[]> DOUBLE_ARRAY = new DoubleArrayDataType();
    PersistentDataType<byte[], float[]> FLOAT_ARRAY = new FloatArrayDataType();
    PersistentDataType<byte[], short[]> SHORT_ARRAY = new ShortArrayDataType();

    /*
    Already existing
     */
    PersistentDataType<Byte, Byte> BYTE = PersistentDataType.BYTE;
    PersistentDataType<byte[], byte[]> BYTE_ARRAY = PersistentDataType.BYTE_ARRAY;
    PersistentDataType<Double, Double> DOUBLE = PersistentDataType.DOUBLE;
    PersistentDataType<Float, Float> FLOAT = PersistentDataType.FLOAT;
    PersistentDataType<Integer, Integer> INTEGER = PersistentDataType.INTEGER;
    PersistentDataType<int[], int[]> INTEGER_ARRAY = PersistentDataType.INTEGER_ARRAY;
    PersistentDataType<Long, Long> LONG = PersistentDataType.LONG;
    PersistentDataType<long[], long[]> LONG_ARRAY = PersistentDataType.LONG_ARRAY;
    PersistentDataType<Short, Short> SHORT = PersistentDataType.SHORT;
    PersistentDataType<String, String> STRING = PersistentDataType.STRING;
    PersistentDataType<PersistentDataContainer, PersistentDataContainer> TAG_CONTAINER = PersistentDataType.TAG_CONTAINER;
    PersistentDataType<PersistentDataContainer[], PersistentDataContainer[]> TAG_CONTAINER_ARRAY = PersistentDataType.TAG_CONTAINER_ARRAY;

    /*
    Collections
     */
    static <C extends Collection<D>,D> PersistentDataType<PersistentDataContainer, C> asGenericCollection(Class<C> collectionClazz, PersistentDataType<?,D> type) {
        return new DataCollection(collectionClazz, type);
    }

    static <D> PersistentDataType<PersistentDataContainer,ArrayList<D>> asArrayList(PersistentDataType<?,D> type) {
        return new DataCollection(ArrayList.class, type);
    }

    static <D> PersistentDataType<PersistentDataContainer, List<D>> asList(PersistentDataType<?,D> type) {
        return new DataCollection(ArrayList.class, type);
    }

    static <D> PersistentDataType<PersistentDataContainer, HashSet<D>> asHashSet(PersistentDataType<?,D> type) {
        return new DataCollection(HashSet.class, type);
    }

    static <D> PersistentDataType<PersistentDataContainer, Set<D>> asSet(PersistentDataType<?,D> type) {
        return new DataCollection(HashSet.class, type);
    }

    /*
    Maps
     */
    static <M extends Map<K,V>,K,V> PersistentDataType<PersistentDataContainer, Map<K,V>> asGenericMap(Class<? extends M> mapClazz, PersistentDataType<?,K> keyType, PersistentDataType<?,V> valueType) {
        return new DataMap(mapClazz, keyType, valueType);
    }

    static <K,V> PersistentDataType<PersistentDataContainer,Map<K,V>> asMap(PersistentDataType<?,K> keyType, PersistentDataType<?,V> valueType) {
        return new DataMap(HashMap.class, keyType, valueType);
    }

    static <K,V> PersistentDataType<PersistentDataContainer,HashMap<K,V>> asHashMap(PersistentDataType<?,K> keyType, PersistentDataType<?,V> valueType) {
        return new DataMap(HashMap.class, keyType, valueType);
    }

    static <K,V> PersistentDataType<PersistentDataContainer,LinkedHashMap<K,V>> asLinkedHashMap(PersistentDataType<?,K> keyType, PersistentDataType<?,V> valueType) {
        return new DataMap(LinkedHashMap.class, keyType, valueType);
    }




}
