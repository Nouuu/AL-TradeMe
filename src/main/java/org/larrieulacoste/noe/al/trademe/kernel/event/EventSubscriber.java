package org.larrieulacoste.noe.al.trademe.kernel.event;

public interface EventSubscriber<E extends Event<?>> {
    void accept(E event);
}
