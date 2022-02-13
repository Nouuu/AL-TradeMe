package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.Objects;

public record TradesmanRegistered(
        EventId eventId,
        ZonedDateTime occurredDate,
        TradesmanEventEntity tradesman
) implements ApplicationEvent {

    public TradesmanRegistered {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(occurredDate);
        Objects.requireNonNull(tradesman);
    }

    public static TradesmanRegistered withTradesman(TradesmanEventEntity tradesman) {
        return new TradesmanRegistered(EventId.create(), ZonedDateTime.now(), tradesman);
    }

}
