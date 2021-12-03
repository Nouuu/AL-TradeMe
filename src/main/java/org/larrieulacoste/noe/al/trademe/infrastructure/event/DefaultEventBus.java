package org.larrieulacoste.noe.al.trademe.infrastructure.event;

import org.larrieulacoste.noe.al.trademe.domain.event.Event;
import org.larrieulacoste.noe.al.trademe.domain.event.EventBus;
import org.larrieulacoste.noe.al.trademe.domain.event.Subscriber;
import org.larrieulacoste.noe.al.trademe.domain.logger.Logger;
import org.larrieulacoste.noe.al.trademe.domain.logger.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DefaultEventBus<E extends Event<?>> implements EventBus<E> {

    private final List<Subscriber<E>> subscribers;
    private final Logger logger;

    public DefaultEventBus(LoggerFactory loggerFactory) {
        this.subscribers = new ArrayList<>();
        this.logger = Objects.requireNonNull(loggerFactory).getLogger(this);
    }

    @Override
    public void send(E event) {
        logger.log("New event sent : " + event);

        if (event == null) {
            throw new NullPointerException("Event is null !");
        }
        if (subscribers.isEmpty()) {
            throw new IllegalStateException("No subscriber for " + event.getClass().getSimpleName());
        }

        subscribers.forEach(subscriber -> subscriber.accept(event));
    }

    @Override
    public void registerSubscriber(Subscriber<E> givenSubscriber) {
        if (givenSubscriber != null && !subscribers.contains(givenSubscriber)) {
            subscribers.add(givenSubscriber);
        }
    }
}
