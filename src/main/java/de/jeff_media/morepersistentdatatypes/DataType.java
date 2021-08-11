package de.jeff_media.morepersistentdatatypes;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.data.BlockData;
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

public class DataType {

    /*
    Custom types
     */
    public static final PersistentDataType<byte[], AttributeModifier> ATTRIBUTE_MODIFIER = new ConfigurationSerializableDataType<>(AttributeModifier.class);
    public static final PersistentDataType<byte[], AttributeModifier[]> ATTRIBUTE_MODIFIER_ARRAY = new ConfigurationSerializableArrayDataType<>(AttributeModifier.class, AttributeModifier[].class);
    public static final PersistentDataType<byte[], BlockData> BLOCK_DATA = new BlockDataDataType();
    public static final PersistentDataType<byte[], BlockVector> BLOCK_VECTOR = new ConfigurationSerializableDataType<>(BlockVector.class);
    public static final PersistentDataType<byte[], BlockVector[]> BLOCK_VECTOR_ARRAY = new ConfigurationSerializableArrayDataType<>(BlockVector.class, BlockVector[].class);
    public static final PersistentDataType<byte[], BoundingBox> BOUNDING_BOX = new ConfigurationSerializableDataType<>(BoundingBox.class);
    public static final PersistentDataType<byte[], BoundingBox[]> BOUNDING_BOX_ARRAY = new ConfigurationSerializableArrayDataType<>(BoundingBox.class, BoundingBox[].class);
    public static final PersistentDataType<byte[], Color> COLOR = new ConfigurationSerializableDataType<>(Color.class);
    public static final PersistentDataType<byte[], Color[]> COLOR_ARRAY = new ConfigurationSerializableArrayDataType<>(Color.class, Color[].class);
    public static final PersistentDataType<byte[], ConfigurationSerializable> CONFIGURATION_SERIALIZABLE = new ConfigurationSerializableDataType<>(ConfigurationSerializable.class);
    public static final PersistentDataType<byte[], ConfigurationSerializable[]> CONFIGURATION_SERIALIZABLE_ARRAY = new ConfigurationSerializableArrayDataType<>(ConfigurationSerializable.class, ConfigurationSerializable[].class);
    public static final PersistentDataType<byte[], FireworkEffect> FIREWORK_EFFECT = new ConfigurationSerializableDataType<>(FireworkEffect.class);
    public static final PersistentDataType<byte[], FireworkEffect[]> FIREWORK_EFFECT_ARRAY = new ConfigurationSerializableArrayDataType<>(FireworkEffect.class, FireworkEffect[].class);
    public static final PersistentDataType<byte[], ItemMeta> ITEM_META = new ConfigurationSerializableDataType<>(ItemMeta.class);
    public static final PersistentDataType<byte[], ItemMeta[]> ITEM_META_ARRAY = new ConfigurationSerializableArrayDataType<>(ItemMeta.class, ItemMeta[].class);
    public static final PersistentDataType<byte[], ItemStack> ITEM_STACK = new ConfigurationSerializableDataType<>(ItemStack.class);
    public static final PersistentDataType<byte[], ItemStack[]> ITEM_STACK_ARRAY = new ConfigurationSerializableArrayDataType<>(ItemStack.class, ItemStack[].class);
    public static final PersistentDataType<byte[], Location> LOCATION = new ConfigurationSerializableDataType<>(Location.class);
    public static final PersistentDataType<byte[], Location[]> LOCATION_ARRAY = new ConfigurationSerializableArrayDataType<>(Location.class, Location[].class);
    public static final PersistentDataType<byte[], OfflinePlayer> OFFLINE_PLAYER = new ConfigurationSerializableDataType<>(OfflinePlayer.class);
    public static final PersistentDataType<byte[], OfflinePlayer[]> OFFLINE_PLAYER_ARRAY = new ConfigurationSerializableArrayDataType<>(OfflinePlayer.class, OfflinePlayer[].class);
    public static final PersistentDataType<byte[], Pattern> PATTERN = new ConfigurationSerializableDataType<>(Pattern.class);
    public static final PersistentDataType<byte[], Pattern[]> PATTERN_ARRAY = new ConfigurationSerializableArrayDataType<>(Pattern.class, Pattern[].class);
    public static final PersistentDataType<byte[], Player> PLAYER = new ConfigurationSerializableDataType<>(Player.class);
    public static final PersistentDataType<byte[], Player[]> PLAYER_ARRAY = new ConfigurationSerializableArrayDataType<>(Player.class, Player[].class);
    public static final PersistentDataType<byte[], PotionEffect> POTION_EFFECT = new ConfigurationSerializableDataType<>(PotionEffect.class);
    public static final PersistentDataType<byte[], PotionEffect[]> POTION_EFFECT_ARRAY = new ConfigurationSerializableArrayDataType<>(PotionEffect.class, PotionEffect[].class);
    public static final PersistentDataType<byte[], String[]> STRING_ARRAY = new StringArrayDataType(StandardCharsets.UTF_8);
    public static final PersistentDataType<byte[], java.util.UUID> UUID = new UuidDataType();
    public static final PersistentDataType<byte[], Vector> VECTOR = new ConfigurationSerializableDataType<>(Vector.class);
    public static final PersistentDataType<byte[], Vector[]> VECTOR_ARRAY = new ConfigurationSerializableArrayDataType<>(Vector.class, Vector[].class);

    /*
    Missing primitives and primitive arrays
    */
    public static final PersistentDataType<Byte, Boolean> BOOLEAN = new BooleanDataType();
    public static final PersistentDataType<byte[], boolean[]> BOOLEAN_ARRAY = new BooleanArrayDataType();
    public static final PersistentDataType<Integer, Character> CHARACTER = new CharDataType();
    public static final PersistentDataType<int[], char[]> CHARACTER_ARRAY = new CharArrayDataType();
    public static final PersistentDataType<byte[], double[]> DOUBLE_ARRAY = new DoubleArrayDataType();
    public static final PersistentDataType<byte[], float[]> FLOAT_ARRAY = new FloatArrayDataType();
    public static final PersistentDataType<byte[], short[]> SHORT_ARRAY = new ShortArrayDataType();

    /*
    Already existing
     */
    public static final PersistentDataType<Byte, Byte> BYTE = PersistentDataType.BYTE;
    public static final PersistentDataType<byte[], byte[]> BYTE_ARRAY = PersistentDataType.BYTE_ARRAY;
    public static final PersistentDataType<Double, Double> DOUBLE = PersistentDataType.DOUBLE;
    public static final PersistentDataType<Float, Float> FLOAT = PersistentDataType.FLOAT;
    public static final PersistentDataType<Integer, Integer> INTEGER = PersistentDataType.INTEGER;
    public static final PersistentDataType<int[], int[]> INTEGER_ARRAY = PersistentDataType.INTEGER_ARRAY;
    public static final PersistentDataType<Long, Long> LONG = PersistentDataType.LONG;
    public static final PersistentDataType<long[], long[]> LONG_ARRAY = PersistentDataType.LONG_ARRAY;
    public static final PersistentDataType<Short, Short> SHORT = PersistentDataType.SHORT;
    public static final PersistentDataType<String, String> STRING = PersistentDataType.STRING;
    public static final PersistentDataType<PersistentDataContainer, PersistentDataContainer> TAG_CONTAINER = PersistentDataType.TAG_CONTAINER;
    public static final PersistentDataType<PersistentDataContainer[], PersistentDataContainer[]> TAG_CONTAINER_ARRAY = PersistentDataType.TAG_CONTAINER_ARRAY;

}
