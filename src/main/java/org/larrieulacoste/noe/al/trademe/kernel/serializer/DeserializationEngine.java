package org.larrieulacoste.noe.al.trademe.kernel.serializer;

public interface DeserializationEngine {
    <T> T apply(String strObject, Class<T> objectClass);
}
