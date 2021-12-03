package org.larrieulacoste.noe.al.trademe.kernel.event;

import org.larrieulacoste.noe.al.trademe.domain.logger.Logger;
import org.larrieulacoste.noe.al.trademe.domain.logger.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DefaultEventBus<E extends Event<?>> implements EventBus<E> {

    //    private final List<EventSubscriber<E>> eventSubscribers;
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

/*
    @Override
    public void registerSubscriber(EventSubscriber<E> givenEventSubscriber) {
        if (givenEventSubscriber != null && !eventSubscribers.contains(givenEventSubscriber)) {
            eventSubscribers.add(givenEventSubscriber);
        }
    }
*/

    @Override
    public void register(Class<? extends E> eventClass, EventSubscriber<? extends E> eventSubscriber) {

    }

    @Override
    public void registerMultipleSubscribers(Class<? extends E> eventClass, List<EventSubscriber<? extends E>> eventSubscribers) {

    }
}
