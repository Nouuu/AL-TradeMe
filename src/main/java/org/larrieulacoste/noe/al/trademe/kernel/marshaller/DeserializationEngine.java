package org.larrieulacoste.noe.al.trademe.kernel.marshaller;

public interface DeserializationEngine {
    <T> T apply(String strObject, Class<T> objectClass);
}
