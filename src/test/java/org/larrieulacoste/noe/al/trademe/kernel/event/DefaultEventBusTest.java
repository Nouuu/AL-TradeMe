package org.larrieulacoste.noe.al.trademe.kernel.event;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class DefaultEventBusTest {

    private Map<Class<? extends Event>, List<EventSubscriber<? extends Event>>> associatedSubscribers;
    private EventBus<Event> eventBus;
    private EventSubscriber eventSubscriber;
    private List<EventSubscriber<? extends Event>> eventSubscribers;

    @SuppressWarnings("all")
    @BeforeEach
    void setUp() {
        eventSubscriber = new TestEventSubscriber();
        eventSubscribers = new ArrayList<>();
        eventSubscribers.add(eventSubscriber);
        associatedSubscribers = new HashMap<>();
        associatedSubscribers.put(TestEvent.class, eventSubscribers);
        eventBus = new DefaultEventBus<>(associatedSubscribers);
    }

    @Test
    void publish() {
        Event event = new TestEvent();
        Assertions.assertThatThrownBy(() -> eventBus.publish(event))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Success !");
    }

    @Test
    void publishNull() {
        Assertions.assertThatThrownBy(() -> eventBus.publish(null))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Event is null !");
    }

    @Test
    void register() {
    }

    @Test
    void unregister() {
    }

    @Test
    void registerMultipleSubscribers() {
    }

    @Test
    void unregisterMultipleSubscribers() {
    }
}