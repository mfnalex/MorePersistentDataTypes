package com.jeff_media.morepersistentdatatypes;

import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

/**
 * Represents a generic PersistentDataType. You can provide two functions for converting between the primitive and complex type.
 * @param <T> Primitive Type
 * @param <Z> Complex Type
 */
public class GenericDataType <T, Z> implements PersistentDataType<T, Z> {

    private static final Class<?>[] ALLOWED_TYPES = new Class[] {
            Byte.class,
            byte[].class,
            Double.class,
            Float.class,
            Integer.class,
            int[].class,
            Long.class,
            long[].class,
            Short.class,
            String.class,
            PersistentDataContainer.class,
            PersistentDataContainer[].class
    };

    private final Class<T> primitiveType;
    private final Class<Z> complexType;
    private final Function<T,Z> toComplex;
    private final Function<Z,T> toPrimitive;


    /**
     * Creates a new generic PersistentDataType.
     * @param primitiveType Primitive type. Must be either byte, byte[], double, float, int, int[], long, long[], short, String, PersistentDataContainer or PersistentDataContainer[]
     * @param complexType Complex type
     * @param toComplex Function to convert the primitive to the complex type
     * @param toPrimitive Function to convert the complex to the primitive type
     */
    public GenericDataType(final Class<T> primitiveType, final Class<Z> complexType, final Function<T, Z> toComplex, final Function<Z,T> toPrimitive) {
        boolean typeOk = false;
        for(final Class<?> clazz : ALLOWED_TYPES) {
            if(clazz.equals(primitiveType)) {
                typeOk = true;
                break;
            }
        }
        if(!typeOk) {
            throw new IllegalArgumentException(primitiveType.getName() + " is not a valid primitive type.");
        }
        this.primitiveType = primitiveType;
        this.complexType = complexType;
        this.toComplex = toComplex;
        this.toPrimitive = toPrimitive;
    }

    @NotNull
    @Override
    public Class<T> getPrimitiveType() {
        return primitiveType;
    }

    @NotNull
    @Override
    public Class<Z> getComplexType() {
        return complexType;
    }

    @NotNull
    @Override
    public T toPrimitive(@NotNull final Z z, @NotNull final PersistentDataAdapterContext persistentDataAdapterContext) {
        return toPrimitive.apply(z);
    }

    @NotNull
    @Override
    public Z fromPrimitive(@NotNull final T t, @NotNull final PersistentDataAdapterContext persistentDataAdapterContext) {
        return toComplex.apply(t);
    }
}
