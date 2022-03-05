package org.larrieulacoste.noe.al.trademe.kernel.marshaller;

public interface SerializationEngine {
    <T> String apply(T object);
}
