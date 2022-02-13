package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.Objects;

public record TradesmanDeleted(EventId eventId,
                               ZonedDateTime occurredDate,
                               TradesmanEventEntity tradesman) implements ApplicationEvent {

    public TradesmanDeleted {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(occurredDate);
        Objects.requireNonNull(tradesman);
    }

    public static TradesmanDeleted withTradesman(TradesmanEventEntity tradesman) {
        return new TradesmanDeleted(EventId.create(), ZonedDateTime.now(), tradesman);
    }
}
