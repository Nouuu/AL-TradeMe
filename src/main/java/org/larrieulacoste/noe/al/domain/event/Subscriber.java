package org.larrieulacoste.noe.al.domain.event;

import java.util.function.Consumer;

public interface Subscriber<E extends Event<?>> extends Consumer<E> {
}
