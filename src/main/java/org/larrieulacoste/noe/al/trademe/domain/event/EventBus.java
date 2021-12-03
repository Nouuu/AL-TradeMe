package org.larrieulacoste.noe.al.trademe.domain.event;

public interface EventBus<E extends Event<?>> {
    void send(E event);

    void registerSubscriber(Subscriber<E> subscriber);
}
