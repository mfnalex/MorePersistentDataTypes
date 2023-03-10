package com.jeff_media.morepersistentdatatypes;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Supplier;

public class TestUtils {

    private TestUtils() {

    }

    @SafeVarargs
    public static <E, T extends Collection<E>> T collectionOf(Supplier<T> supplier, E... elements) {
        T collection = supplier.get();
        collection.addAll(Arrays.asList(elements));
        return collection;
    }
}
