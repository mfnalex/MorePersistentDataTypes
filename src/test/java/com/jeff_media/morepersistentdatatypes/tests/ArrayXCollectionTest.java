package com.jeff_media.morepersistentdatatypes.tests;

import com.jeff_media.morepersistentdatatypes.DataType;
import com.jeff_media.morepersistentdatatypes.TestUtils;
import com.jeff_media.morepersistentdatatypes.UnitTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class ArrayXCollectionTest extends UnitTest {

    @Test
    public void testArrayToCollection() {
        pdc.set(key, DataType.asArray(new String[0], DataType.STRING), new String[] {"a", "b"});
        List<String> list = pdc.get(key, DataType.asList(DataType.STRING));
        Assertions.assertEquals(TestUtils.collectionOf(ArrayList<String>::new, "a", "b"), list);
        Assertions.assertNotEquals(TestUtils.collectionOf(ArrayList<String>::new, "a", "c"), list);
    }

    @Test
    public void testCollectionToArray() {
        pdc.set(key, DataType.asList(DataType.STRING), TestUtils.collectionOf(ArrayList<String>::new, "a", "b"));
        Assertions.assertArrayEquals(new String[] {"a", "b"}, pdc.get(key, DataType.asArray(new String[0], DataType.STRING)));
        Assertions.assertThrows(AssertionError.class, () -> Assertions.assertArrayEquals(new String[] {"a", "c"}, pdc.get(key, DataType.asArray(new String[0], DataType.STRING))));
    }
}
