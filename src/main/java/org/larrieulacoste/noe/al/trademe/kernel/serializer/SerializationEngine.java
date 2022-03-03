package org.larrieulacoste.noe.al.trademe.kernel.serializer;

public interface SerializationEngine {
    <T> String apply(T object);
}
