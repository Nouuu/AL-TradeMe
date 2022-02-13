package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.Objects;

public record TradesmanSubscriptionPaymentFailure(
        EventId eventId,
        ZonedDateTime occurredDate,
        TradesmanEventEntity tradesman
) implements ApplicationEvent {

    public TradesmanSubscriptionPaymentFailure {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(occurredDate);
        Objects.requireNonNull(tradesman);
    }

    public static TradesmanSubscriptionPaymentFailure withTradesman(TradesmanEventEntity tradesman) {
        return new TradesmanSubscriptionPaymentFailure(EventId.create(), ZonedDateTime.now(), tradesman);
    }

}
