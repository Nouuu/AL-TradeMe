package org.larrieulacoste.noe.al.trademe.kernel.event;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class EventIdTest {

    @Test
    void create() {
        Assertions.assertThat(EventId.create())
                .isNotNull();
    }

    @Test
    void testToString() {
        EventId eventId = EventId.create();
        String eventIdValue = eventId.value;
        Assertions.assertThat(eventIdValue).isNotEmpty();
        Assertions.assertThat(eventId).hasToString("EventId{value='" + eventIdValue + "'}");
    }

    @Test
    void testEquals() {
        EventId eventId = EventId.create();
        EventId otherEventId = EventId.create();
        Assertions.assertThat(eventId.equals(otherEventId)).isFalse();
    }

    @Test
    void testHashCode() {
        EventId eventId = EventId.create();
        Assertions.assertThat(eventId.hashCode()).isNotZero();
    }
}