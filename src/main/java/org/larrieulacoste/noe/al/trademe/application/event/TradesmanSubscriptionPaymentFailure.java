package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.Objects;

public record TradesmanSubscriptionPaymentFailure(
        EventId eventId,
        ZonedDateTime occurredDate,
        EntityId tradesmanId
) implements ApplicationEvent {

    public TradesmanSubscriptionPaymentFailure {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(occurredDate);
        Objects.requireNonNull(tradesmanId);
    }

    public static TradesmanSubscriptionPaymentFailure of(EntityId tradesmanId) {
        return new TradesmanSubscriptionPaymentFailure(EventId.create(), ZonedDateTime.now(), tradesmanId);
    }

}
