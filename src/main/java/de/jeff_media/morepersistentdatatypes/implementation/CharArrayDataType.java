package de.jeff_media.morepersistentdatatypes.implementation;

import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class CharArrayDataType implements PersistentDataType<int[], char[]> {
    @NotNull
    @Override
    public Class<int[]> getPrimitiveType() {
        return int[].class;
    }

    @NotNull
    @Override
    public Class<char[]> getComplexType() {
        return char[].class;
    }

    @Override
    public int @NotNull [] toPrimitive(char[] chars, @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
        int[] ints = new int[chars.length];
        for(int i = 0; i < chars.length; i++) {
            ints[i] = chars[i];
        }
        return ints;
    }

    @Override
    public char @NotNull [] fromPrimitive(int[] ints, @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
        char[] chars = new char[ints.length];
        for(int i = 0; i < ints.length; i++) {
            chars[i] = (char) ints[i];
        }
        return chars;
    }
}
