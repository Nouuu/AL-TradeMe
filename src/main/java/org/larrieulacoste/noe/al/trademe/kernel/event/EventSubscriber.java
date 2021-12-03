package org.larrieulacoste.noe.al.trademe.kernel.event;

import java.util.function.Consumer;

public interface EventSubscriber<E extends Event<?>> extends Consumer<E> {
}
