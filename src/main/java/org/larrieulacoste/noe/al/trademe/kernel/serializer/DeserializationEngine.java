package org.larrieulacoste.noe.al.trademe.kernel.serializer;

import java.util.function.Function;

public interface DeserializationEngine<T> extends Function<String, T> {
}
