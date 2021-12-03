package org.larrieulacoste.noe.al.trademe.kernel.event;

public interface EventBus<E extends Event<?>> {
    void send(E event);

    void registerSubscriber(EventSubscriber<E> eventSubscriber);
}
