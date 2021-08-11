import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.HashMap;
import java.util.Map;

/**
 * IMPORTANT: Please note that you HAVE to register your serializable objects in your onLoad or onEnable using
 * ConfigurationSerialization.registerClass(YourClass.class);
 */
public class SerializablePerson implements ConfigurationSerializable {

    String name;
    int age;

    @Override
    public Map<String, Object> serialize() {
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        map.put("age",age);
        return map;
    }

    public static SerializablePerson deserialize(Map<String,Object> map) {
        return new SerializablePerson((String)map.get("name"),(int)map.get("age"));
    }

    @Override
    public String toString() {
        return "SerializablePerson{name='" + name + "', age=" + age + "}";
    }

    public SerializablePerson(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
