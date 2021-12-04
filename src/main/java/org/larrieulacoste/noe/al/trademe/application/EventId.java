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
}
