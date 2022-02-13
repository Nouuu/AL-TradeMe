package org.larrieulacoste.noe.al.trademe.kernel.event;

import java.time.ZonedDateTime;

public class TestEvent2 implements Event {
    @Override
    public EventId eventId() {
        return EventId.create();
    }

    @Override
    public ZonedDateTime occurredDate() {
        return ZonedDateTime.now();
    }
}
