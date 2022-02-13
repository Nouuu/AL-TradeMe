package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.Objects;

public record TradesmanNewRegistration(
        EventId eventId,
        ZonedDateTime occurredDate,
        TradesmanEventEntity tradesman
) implements ApplicationEvent {

    public TradesmanNewRegistration {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(occurredDate);
        Objects.requireNonNull(tradesman);
    }

    public static TradesmanNewRegistration of(TradesmanEventEntity tradesmanEventEntity) {
        return new TradesmanNewRegistration(EventId.create(), ZonedDateTime.now(), tradesmanEventEntity);
    }

}
