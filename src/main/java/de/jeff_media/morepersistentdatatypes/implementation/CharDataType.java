package de.jeff_media.morepersistentdatatypes.implementation;

import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class CharDataType implements PersistentDataType<Integer, Character> {
    @NotNull
    @Override
    public Class<Integer> getPrimitiveType() {
        return Integer.class;
    }

    @NotNull
    @Override
    public Class<Character> getComplexType() {
        return Character.class;
    }

    @NotNull
    @Override
    public Integer toPrimitive(@NotNull Character character, @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
        return (int) character;
    }

    @NotNull
    @Override
    public Character fromPrimitive(@NotNull Integer integer, @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
        return (char) integer.intValue();
    }
}
