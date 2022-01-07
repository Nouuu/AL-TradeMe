package org.larrieulacoste.noe.al.trademe.kernel.event;

import java.time.ZonedDateTime;

public class TestEvent implements Event{
    @Override
    public EventId getEventId() {
        return EventId.create();
    }

    @Override
    public ZonedDateTime getOccurredDate() {
        return ZonedDateTime.now();
    }
}
