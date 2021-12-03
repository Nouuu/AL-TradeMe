package org.larrieulacoste.noe.al.trademe.kernel.event;

import org.larrieulacoste.noe.al.trademe.domain.logger.Logger;
import org.larrieulacoste.noe.al.trademe.domain.logger.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DefaultEventBus<E extends Event<?>> implements EventBus<E> {

    private final List<EventSubscriber<E>> eventSubscribers;
    private final Logger logger;

    public DefaultEventBus(LoggerFactory loggerFactory) {
        this.eventSubscribers = new ArrayList<>();
        this.logger = Objects.requireNonNull(loggerFactory).getLogger(this);
    }

    @Override
    public void send(E event) {
        logger.log("New event sent : " + event);

        if (event == null) {
            throw new NullPointerException("Event is null !");
        }
        if (eventSubscribers.isEmpty()) {
            throw new IllegalStateException("No subscriber for " + event.getClass().getSimpleName());
        }

        eventSubscribers.forEach(eventSubscriber -> eventSubscriber.accept(event));
    }

    @Override
    public void registerSubscriber(EventSubscriber<E> givenEventSubscriber) {
        if (givenEventSubscriber != null && !eventSubscribers.contains(givenEventSubscriber)) {
            eventSubscribers.add(givenEventSubscriber);
        }
    }
}
