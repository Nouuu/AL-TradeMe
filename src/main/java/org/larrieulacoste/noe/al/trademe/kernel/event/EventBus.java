package org.larrieulacoste.noe.al.trademe.kernel.event;

import java.util.List;

public interface EventBus<E extends Event> {
    void publish(E event);

    void register(Class<? extends E> eventClass, EventSubscriber<? extends E> eventSubscriber);

    void registerMultipleSubscribers(Class<? extends E> eventClass, List<EventSubscriber<? extends E>> eventSubscribers);

    void unregister(Class<? extends E> eventClass, EventSubscriber<? extends E> eventSubscriber);

    void unregisterMultipleSubscribers(Class<? extends E> eventClass, List<EventSubscriber<? extends E>> eventSubscribers);
}
