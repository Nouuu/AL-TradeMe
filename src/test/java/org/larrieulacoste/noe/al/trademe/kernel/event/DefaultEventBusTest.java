package org.larrieulacoste.noe.al.trademe.kernel.event;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.larrieulacoste.noe.al.trademe.kernel.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class DefaultEventBusTest {

    private Map<Class<? extends Event>, List<EventSubscriber<? extends Event>>> associatedSubscribers;
    private EventBus<Event> eventBus;
    private EventSubscriber<? extends Event> eventSubscriber;
    private List<EventSubscriber<? extends Event>> eventSubscribers;
    private Logger eventBusLogger;

    @SuppressWarnings("all")
    @BeforeEach
    void setUp() {
        eventSubscriber = new TestEventSubscriber();
        eventSubscribers = new ArrayList<>();
        eventSubscribers.add(eventSubscriber);
        associatedSubscribers = new HashMap<>();
        associatedSubscribers.put(TestEvent.class, eventSubscribers);
        eventBusLogger = new TestLogger();
        eventBus = new DefaultEventBus<>(associatedSubscribers, eventBusLogger);
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
    void publishNullSubscribers() {
        Event event = new TestEvent2();
        org.junit.jupiter.api.Assertions.assertAll(() -> eventBus.publish(event));
    }

    @Test
    void publishEmptySubscribers() {
        Event event = new TestEvent();
        eventBus.unregister(TestEvent.class, eventSubscriber);
        org.junit.jupiter.api.Assertions.assertAll(() -> eventBus.publish(event));
    }

    @Test
    void register() {
        TestEventSubscriber otherSubscriber = new TestEventSubscriber();
        eventBus.register(TestEvent2.class, otherSubscriber);
        eventBus.register(TestEvent2.class, otherSubscriber);
        Assertions.assertThat(associatedSubscribers.get(TestEvent2.class)).containsExactly(otherSubscriber);
    }

    @Test
    void unregister() {
        eventBus.unregister(TestEvent.class, eventSubscriber);
        Assertions.assertThat(associatedSubscribers.get(TestEvent.class)).isEmpty();
    }

    @Test
    void unregisterNull() {
        eventBus.unregister(TestEvent2.class, eventSubscriber);
        Assertions.assertThat(associatedSubscribers.get(TestEvent.class)).containsExactly(eventSubscriber);
    }

    @Test
    void registerMultipleSubscribers() {
        TestEventSubscriber otherSubscriber = new TestEventSubscriber();
        TestEventSubscriber otherSubscriber2 = new TestEventSubscriber();
        TestEventSubscriber otherSubscriber3 = new TestEventSubscriber();

        eventBus.registerMultipleSubscribers(TestEvent2.class,
                List.of(otherSubscriber, otherSubscriber2, otherSubscriber3));
        Assertions.assertThat(associatedSubscribers.get(TestEvent2.class))
                .containsExactlyElementsOf(List.of(otherSubscriber, otherSubscriber2, otherSubscriber3));
    }

    @Test
    void unregisterMultipleSubscribers() {
        TestEventSubscriber otherSubscriber = new TestEventSubscriber();
        TestEventSubscriber otherSubscriber2 = new TestEventSubscriber();
        TestEventSubscriber otherSubscriber3 = new TestEventSubscriber();

        eventBus.registerMultipleSubscribers(TestEvent2.class,
                List.of(otherSubscriber, otherSubscriber2, otherSubscriber3));
        eventBus.unregisterMultipleSubscribers(TestEvent2.class,
                List.of(otherSubscriber, otherSubscriber2, otherSubscriber3));
        Assertions.assertThat(associatedSubscribers.get(TestEvent2.class)).isEmpty();
    }
}