package com.jeff_media.morepersistentdatatypes.tests;

import com.jeff_media.morepersistentdatatypes.DataType;
import com.jeff_media.morepersistentdatatypes.UnitTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapToArrayTest extends UnitTest {

    @Test
    public void testMapToArrayThrows() {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        pdc.set(key, DataType.asMap(DataType.STRING, DataType.INTEGER), map);
        Assertions.assertThrows(IllegalArgumentException.class, () -> pdc.get(key, DataType.asList(DataType.STRING)));
    }
}
