# MorePersistentDataTypes
**MorePersistentDataTypes** is a tiny library that provides a ton of new *PersistentDataTypes* to use in conjunction
with Bukkit's *PersistentDataContainer*. **It also allows you to use any kind of Collection or Map to store your data.**

## Features

- Adds new PersistentDataTypes for ItemStacks, YamlConfigurations, UUIDs, Locations, and much more!
- **Adds all kinds of Collections and Maps** as PersistentDataType!
  - Of course also supports unlimited levels of nested Collections like `LinkedHashMap<String,List<ItemStack>>` 
  - See below for more information

It is also possible to easily create your own PersistentDataTypes for your custom objects. When they already implement ConfigurationSerializable, it's only one line of code!

It also has the default data types built in, so you can access everything from one class. See at the bottom for a list of all new data types.

[Related SpigotMC thread](https://hub.jeff-media.com/nexus/repository/jeff-media-public/)

## Example
You want to save an ItemStack inside a PersistentDataContainer - normally you would have to serialize the ItemStack to a byte array first, or worse, to a base64 String. With **MorePersistentDataTypes**, you can simply do this:

```java
pdc.set(someNamespacedKey, DataType.ITEM_STACK, myItemStack);
```

Furthermore, you can store EVERYTHING that implements ConfigurationSerializable using DataType.CONFIGURATION_SERIALIZABLE.

## Maven
### Repository
```xml
<repository>
    <id>jeff-media-public</id>
    <url>https://hub.jeff-media.com/nexus/repository/jeff-media-public/</url>
</repository>
```
### Dependency
```xml
<dependency>
    <groupId>com.jeff_media</groupId>
    <artifactId>MorePersistentDataTypes</artifactId>
    <version>2.0.0</version>
    <scope>compile</scope>
</dependency>
```
### Relocating and shading
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-shade-plugin</artifactId>
            <version>3.2.4</version>
            <configuration>
                <relocations>
                    <relocation>
                        <pattern>com.jeff_media.morepersistentdatatypes</pattern>
                        <shadedPattern>YOUR.PACKAGE.morepersistentdatatypes</shadedPattern>
                    </relocation>
                </relocations>
            </configuration>
        </plugin>
    </plugins>
</build>
```



## Using Collections, Arrays or Maps
Using collections, arrays or maps is easy. There are predefined methods for certain collection and map types:

```java
Map<String,ItemStack> map = pdc.get(someKey, DataType.asMap(DataType.STRING, DataType.ITEM_STACK));
```

If you want to use a special collection or map class that's not already included, simply pass the class too:

```java
TreeSet<Location> set = pdc.get(someKey, DataType.asGenericCollectino(TreeSet.class, DataType.LOCATION));
```

For arrays, you should the builtin default array DataType if one exists, for example DataType.STRING_ARRAY.
If there is no already existing array DataType, like for UUIDs, you can use the DataType.asArray method:

```java
PersistentDataType<?,UUID[]> uuidArrayDataType = DataType.asArray(new UUID[0], DataType.UUID);
```

## List of all data types
### In addition to the default data types
| Name | Class | Saved as
|---|---|---|---
| BOOLEAN | java.lang.Boolean | byte
| BOOLEAN_ARRAY | java.lang.Boolean[] | byte[]
| CHARACTER | java.lang.Character | int
| CHARACTER_ARRAY | java.lang.Character[] | int[]
| DOUBLE_ARRAY | java.lang.Double | byte[]
| FLOAT_ARRAY | java.lang.Float | byte[]
| SHORT_ARRAY | java.lang.Short | byte[]
| STRING_ARRAY | java.lang.String[] | byte[]

### Custom data types
| Name                             | Saved as | Note    | Class                                                              |
|----------------------------------|----------|---------|--------------------------------------------------------------------|
| ATTRIBUTE_MODIFIER               | byte[]   |         | org.bukkit.attribute.AttributeModifier                             |
| ATTRIBUTE_MODIFIER_ARRAY         | byte[]   |         | org.bukkit.attribute.AttributeModifier[]                           |
| BLOCK_DATA                       | String   |         | org.bukkit.block.data.BlockData                                    |
| BLOCK_VECTOR                     | byte[]   |         | org.bukkit.util.BlockVector                                        |
| BLOCK_VECTOR_ARRAY               | byte[]   |         | org.bukkit.util.BlockVector[]                                      |
| BOUNDING_BOX                     | byte[]   |         | org.bukkit.util.BoundingBox                                        |
| BOUNDING_BOX_ARRAY               | byte[]   |         | org.bukkit.util.BoundingBox[]                                      |
| COLOR                            | byte[]   |         | org.bukkit.Color                                                   |
| COLOR_ARRAY                      | byte[]   |         | org.bukkit.Color[]                                                 |
| CONFIGURATION_SERIALIZABLE       | byte[]   |         | org.bukkit.configuration.serialization.ConfigurationSerializable   |
| CONFIGURATION_SERIALIZABLE_ARRAY | byte[]   |         | org.bukkit.configuration.serialization.ConfigurationSerializable[] |
| DATE                             | long     |         | java.util.Date                                                     |
| FILE_CONFIGURATION               | String   |         | org.bukkit.configuration.file.FileConfiguration                    |
| FIREWORK_EFFECT                  | byte[]   |         | org.bukkit.FireworkEffect                                          |
| FIREWORK_EFFECT_ARRAY            | byte[]   |         | org.bukkit.FireworkEffect[]                                        |
| ITEM_META                        | byte[]   |         | org.bukkit.inventory.meta.ItemMeta                                 |
| ITEM_META_ARRAY                  | byte[]   |         | org.bukkit.inventory.meta.ItemMeta[]                               |
| ITEM_STACK                       | byte[]   |         | org.bukkit.inventory.ItemStack                                     |
| ITEM_STACK_ARRAY                 | byte[]   |         | org.bukkit.inventory.ItemStack[]                                   |
| LOCATION                         | byte[]   |         | org.bukkit.Location                                                |
| LOCATION_ARRAY                   | byte[]   |         | org.bukkit.Location[]                                              |
| OFFLINE_PLAYER                   | byte[]   |         | org.bukkit.OfflinePlayer                                           |
| OFFLINE_PLAYER_ARRAY             | byte[]   |         | org.bukkit.OfflinePlayer[]                                         |
| PATTERN                          | byte[]   |         | org.bukkit.block.banner.Pattern                                    |
| PATTERN_ARRAY                    | byte[]   |         | org.bukkit.block.banner.Pattern[]                                  |
| PLAYER                           | byte[]   |         | org.bukkit.entity.Player                                           |
| PLAYER_ARRAY                     | byte[]   |         | org.bukkit.entity.Player[]                                         |
| PLAYER_PROFILE                   | byte[]   | 1.18.1+ | org.bukkit.profile.PlayerProfile                                   |
| PLAYER_PROFILE_ARRAY             | byte[]   | 1.18.1+ | org.bukkit.profile.PlayerProfile[]                                 |
| POTION_EFFECT                    | byte[]   |         | org.bukkit.potion.PotionEffect                                     |
| POTION_EFFECT_ARRAY              | byte[]   |         | org.bukkit.potion.PotionEffect[]                                   |
| UUID                             | byte[]   |         | java.util.UUID                                                     |
| VECTOR                           | byte[]   |         | org.bukkit.util.Vector                                             |
| VECTOR_ARRAY                     | byte[]   |         | org.bukkit.util.Vector[]                                           |

## Creating your own PersistentDataTypes
### Using ConfigurationSeriazableDataType
When your custom Object already implements ConfigurationSerializable, it's even easier. Just look at the SerializablePerson.java in the examples/ folder.

To make it storable in a PersistentDataContainer, this is all you need:

```java
PersistentDataType<byte[], SerializablePerson> personType = new ConfigurationSerializableDataType<>(SerializablePerson.class);
```

You can also directly store arrays of your own ConfigurationSerializable objects:

```java
PersistentDataType<byte[], SerializablePerson[]> personArrayType = new ConfigurationSerializableArrayDataType<>(SerializablePerson.class, SerializablePerson[].class);
```

### Using GenericDataType
You can also easily create own generic PersistentDataTypes by creating a new `GenericDataType` instance. It expects 4 parameters:

1. The primitive type that's used to store the object
2. The complex type / the class of which you want to store an instance of
3. A method that converts your object into the primitive type
4. A method that converts the primitive type into your object again

For example, this returns a PersistentDataType<Long,Date> that can save `java.util.Date` objects inside a PersistentDataContainer:

```java
PersistentDataType<Long,Date> dateType = new GenericDataType<>(Long.class, Date.class, Date::new, Date::getTime);
```

If you're not familiar with lambdas and/or method references, or if your Functions are more complicated, you can also provide a Function in the oldschool way:

```java
PersistentDataType<Long, Date> dateType = new GenericDataType<>(Long.class, Date.class, new Function<Long, Date>() {
        @Override
        public Date apply(Long aLong) {
            return new Date(aLong);
        }
    }, new Function<Date, Long>() {
        @Override
        public Long apply(Date date) {
            return date.getTime();
        }
    });
```

## Building
Building this requires Java 11.0.0 or higher. It can still be used by Java 8 or higher, though.

## Other libraries by me

### [CustomBlockData](https://github.com/JEFF-Media-GbR/CustomBlockData)
**MorePersistentDataTypes** goes perfectly well together with my **CustomBlockData** library, that provides a PersistentDataContainer for every block in your world - without any external storage needed!

### [Spigot UpdateChecker](https://github.com/JEFF-Media-GbR/Spigot-UpdateChecker)
Powerful **UpdateChecker** for your plugins, with only line of code.

## Discord

If you need help, feel free to join my Discord server and head to #programming-help:

<a href="https://discord.jeff-media.de"><img src="https://api.jeff-media.de/img/discord1.png"></a>

## Donate

If you are using this project in your paid plugins, or if you just want to buy me a coffee, I would be happy over a small donation :)

<a href="https://paypal.me/mfnalex"><img src="https://www.paypalobjects.com/en_US/DK/i/btn/btn_donateCC_LG.gif" border="0" name="submit" title="PayPal - The safer, easier way to pay online!" alt="Donate with PayPal" /></a>

