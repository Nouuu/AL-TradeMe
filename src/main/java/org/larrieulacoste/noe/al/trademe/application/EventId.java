package org.larrieulacoste.noe.al.trademe.application;

import java.util.Objects;
import java.util.UUID;

public final class EventId {

    private final String value;

    private EventId(String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static EventId create() {
        return new EventId(UUID.randomUUID().toString());
    }

    @Override
    public String toString() {
        return "EventId{" +
                "value='" + value + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventId)) return false;

        EventId eventId = (EventId) o;

        return value.equals(eventId.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
