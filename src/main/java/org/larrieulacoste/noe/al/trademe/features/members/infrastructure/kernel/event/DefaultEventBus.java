package org.larrieulacoste.noe.al.trademe.features.members.infrastructure.kernel.event;

import org.larrieulacoste.noe.al.trademe.domain.logger.Logger;
import org.larrieulacoste.noe.al.trademe.domain.logger.LoggerFactory;

import java.util.*;

public class DefaultEventBus<E extends Event> implements EventBus<E> {

    private final Map<Class<? extends E>, List<EventSubscriber<? extends E>>> associatedSubscribers = new HashMap<>();
    private final Logger logger;

    public DefaultEventBus(LoggerFactory loggerFactory) {
        this.logger = Objects.requireNonNull(loggerFactory).getLogger(this);
    }

    @SuppressWarnings("all")
    @Override
    public void publish(E event) {
        logger.log("New event sent : " + event);

        if (event == null) {
            throw new NullPointerException("Event is null !");
        }

        var eventSubscribers = associatedSubscribers.get(event.getClass());

        if (eventSubscribers == null || eventSubscribers.isEmpty()) {
            // Not really an error so do nothing
            // throw new IllegalStateException("No subscriber for " + event.getClass().getSimpleName());
            return;
        }

        eventSubscribers.forEach(eventSubscriber -> ((EventSubscriber) eventSubscriber).accept(event));
    }

    @Override
    public void register(Class<? extends E> eventClass, EventSubscriber<? extends E> eventSubscriber) {
        List<EventSubscriber<? extends E>> subscribers =
                associatedSubscribers.computeIfAbsent(Objects.requireNonNull(eventClass), k -> new ArrayList<>());

        if (!subscribers.contains(Objects.requireNonNull(eventSubscriber))) {
            subscribers.add(eventSubscriber);
        }
    }

    @Override
    public void unregister(Class<? extends E> eventClass, EventSubscriber<? extends E> eventSubscriber) {
        List<EventSubscriber<? extends E>> subscribers = associatedSubscribers.get(Objects.requireNonNull(eventClass));
        if (subscribers != null) {
            subscribers.remove(Objects.requireNonNull(eventSubscriber));
        }
    }

    @Override
    public void registerMultipleSubscribers(Class<? extends E> eventClass, List<EventSubscriber<? extends E>> eventSubscribers) {
        Objects.requireNonNull(eventSubscribers).forEach(eventSubscriber -> register(eventClass, eventSubscriber));
    }

    @Override
    public void unregisterMultipleSubscribers(Class<? extends E> eventClass, List<EventSubscriber<? extends E>> eventSubscribers) {
        Objects.requireNonNull(eventSubscribers).forEach(eventSubscriber -> unregister(eventClass, eventSubscriber));
    }
}
