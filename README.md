# MorePersistentDataTypes & Collections, Maps and Arrays for PDC!
<!--- Buttons start -->
<p align="center">
  <a href="https://www.spigotmc.org/threads/more-persistent-data-types-collections-maps-and-arrays-for-pdc.520677/">
    <img src="https://static.jeff-media.com/img/button_spigotmc_thread.png?3" alt="SpigotMC Thread">
  </a>
  <a href="https://hub.jeff-media.com/javadocs/morepersistentdatatypes">
    <img src="https://static.jeff-media.com/img/button_javadocs.png?3" alt="Javadocs">
  </a>
  <a href="https://discord.jeff-media.com/">
    <img src="https://static.jeff-media.com/img/button_discord.png?3" alt="Discord">
  </a>
  <a href="https://paypal.me/mfnalex">
    <img src="https://static.jeff-media.com/img/button_donate.png?3" alt="Donate">
  </a>
</p>
<!--- Buttons end -->

**MorePersistentDataTypes** is a tiny library that provides a ton of new *PersistentDataTypes* to use in conjunction
with Bukkit's *PersistentDataContainer*. **It also allows you to use any kind of Collection, Map or Array to store your data.**

## Features

- Adds new PersistentDataTypes for ItemStacks, YamlConfigurations, UUIDs, Locations, and much more!
- **Allows to use any kind of Collection, Map or Array** as PersistentDataType!
    - Of course also supports unlimited levels of nested Collections like `LinkedHashMap<String,List<ItemStack>>`
    - See below for more information

It is also possible to easily create your own PersistentDataTypes for your custom objects. When they already implement
ConfigurationSerializable, it's only one line of code!

It also has the default data types built in, so you can access everything from one class. See at the bottom for a list
of all new data types.

[Related SpigotMC thread](https://hub.jeff-media.com/nexus/repository/jeff-media-public/)

## Example

You want to save an ItemStack inside a PersistentDataContainer - normally you would have to serialize the ItemStack to a
byte array first, or worse, to a base64 String. With **MorePersistentDataTypes**, you can simply do this:

```java
pdc.set(someNamespacedKey, DataType.ITEM_STACK,myItemStack);
```

Furthermore, you can store EVERYTHING that implements ConfigurationSerializable using
DataType.CONFIGURATION_SERIALIZABLE.

## Using Collections, Arrays or Maps

Using collections, arrays or maps is easy. There are predefined methods for certain collection and map types:

```java
Map<String, ItemStack> map = pdc.get(someKey, DataType.asMap(DataType.STRING, DataType.ITEM_STACK));
```

If you want to use a special collection or map class that's not already included, simply pass a Supplier that returns
an empty instance of your desired collection or map type. More information can be found in the Javadocs (see button at
the top of this page).

```java
TreeSet<Location> set = pdc.get(someKey, DataType.asGenericCollection(TreeSet::new, DataType.LOCATION));
```

For arrays, you should use the builtin default array DataType if one exists, for example DataType.STRING_ARRAY. If there is
no already existing array DataType, like for UUIDs, you can use the DataType.asArray method:

```java
PersistentDataType<?, UUID[]> uuidArrayDataType = DataType.asArray(new UUID[0], DataType.UUID);
```

## Maven

### Repository

The artifact is available on Maven Central. You don't need to add a repository to your pom.xml.

### Dependency

```xml

<dependency>
    <groupId>com.jeff-media</groupId>
    <artifactId>MorePersistentDataTypes</artifactId>
    <version>2.4.0</version>
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
            <version>3.5.0</version>
            <executions>
                <execution>
                    <phase>package</phase>
                    <goals>
                        <goal>shade</goal>
                    </goals>
                </execution>
            </executions>
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

## Gradle

### Repository

```groovy
repositories {
    mavenCentral()
}
```

### Dependency

```groovy
dependencies {
    implementation 'com.jeff-media:MorePersistentDataTypes:2.4.0'
}
```

### Relocating and shading

Plugins:
```groovy
plugins {
    id 'java'
    id "com.github.johnrengelman.shadow" version "7.1.1"
}
```

ShadowJar:
```groovy
shadowJar {
    relocate 'com.jeff_media.morepersistentdatatypes', 'YOUR.PACKAGE.morepersistentdatatypes'
}
```


## List of all data types

### In addition to the default data types

| Name            | Saved as | Class                 |
|-----------------|----------|-----------------------|
| BOOLEAN         | byte     | java.lang.Boolean     |
| BOOLEAN_ARRAY   | byte[]   | java.lang.Boolean[]   |
| CHARACTER       | int      | java.lang.Character   |
| CHARACTER_ARRAY | int[]    | java.lang.Character[] |
| DOUBLE_ARRAY    | byte[]   | java.lang.Double      |
| FLOAT_ARRAY     | byte[]   | java.lang.Float       |
| SHORT_ARRAY     | byte[]   | java.lang.Short       |
| STRING_ARRAY    | byte[]   | java.lang.String[]    |

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

When your custom Object already implements ConfigurationSerializable, it's even easier. Just look at the
SerializablePerson.java in the examples/ folder.

To make it storable in a PersistentDataContainer, this is all you need:

```java
PersistentDataType<byte[],SerializablePerson> personType = new ConfigurationSerializableDataType<>(SerializablePerson.class);
```

You can also directly store arrays of your own ConfigurationSerializable objects:

```java
PersistentDataType<byte[],SerializablePerson[]> personArrayType = new ConfigurationSerializableArrayDataType<>(SerializablePerson.class,SerializablePerson[].class);
```

### Using GenericDataType

You can also easily create own generic PersistentDataTypes by creating a new `GenericDataType` instance. It expects 4
parameters:

1. The primitive type that's used to store the object
2. The complex type / the class of which you want to store an instance of
3. A method that converts your object into the primitive type
4. A method that converts the primitive type into your object again

For example, this returns a PersistentDataType<Long,Date> that can save `java.util.Date` objects inside a
PersistentDataContainer:

```java
PersistentDataType<Long, Date> dateType = new GenericDataType<>(Long.class,Date.class,Date::new,Date::getTime);
```

## Building

Building this requires Java 17.0.0 or higher. It can still be used by Java 8 or higher, though.

## Javadocs

You can find the Javadocs here: https://hub.jeff-media.com/javadocs/morepersistentdatatypes/

## Other libraries by me

### [CustomBlockData](https://github.com/JEFF-Media-GbR/CustomBlockData)

**MorePersistentDataTypes** goes perfectly well together with my **CustomBlockData** library, that provides a
PersistentDataContainer for every block in your world - without any external storage needed!

### [Spigot UpdateChecker](https://github.com/JEFF-Media-GbR/Spigot-UpdateChecker)

Powerful **UpdateChecker** for your plugins, with only one line of code.

## Discord

If you need help, feel free to join my Discord server and head to #programming-help:

<a href="https://discord.jeff-media.de"><img src="https://api.jeff-media.de/img/discord1.png"></a>

## Donate

If you are using this project in your paid plugins, or if you just want to buy me a coffee, I would be happy over a
small donation :)

<a href="https://paypal.me/mfnalex"><img src="https://www.paypalobjects.com/en_US/DK/i/btn/btn_donateCC_LG.gif" border="0" name="submit" title="PayPal - The safer, easier way to pay online!" alt="Donate with PayPal" /></a>

