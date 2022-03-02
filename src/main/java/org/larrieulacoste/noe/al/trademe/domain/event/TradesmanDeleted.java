package org.larrieulacoste.noe.al.trademe.domain.event;

import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.EntityId;

import java.time.ZonedDateTime;
import java.util.Objects;

public record TradesmanDeleted(EventId eventId,
                               ZonedDateTime occurredDate,
                               EntityId tradesmanId) implements ApplicationEvent {

    public TradesmanDeleted {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(occurredDate);
        Objects.requireNonNull(tradesmanId);
    }

    public static TradesmanDeleted of(EntityId tradesmanId) {
        return new TradesmanDeleted(EventId.create(), ZonedDateTime.now(), tradesmanId);
    }
}
