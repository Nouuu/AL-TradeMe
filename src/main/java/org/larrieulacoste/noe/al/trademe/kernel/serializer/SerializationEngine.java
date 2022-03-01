package org.larrieulacoste.noe.al.trademe.kernel.serializer;

import java.util.function.Function;

public interface SerializationEngine<T> extends Function<T, String> {
}
