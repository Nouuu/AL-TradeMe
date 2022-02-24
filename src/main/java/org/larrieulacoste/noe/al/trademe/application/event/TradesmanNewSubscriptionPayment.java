package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.domain.model.Amount;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.domain.model.PaymentMethod;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.Objects;

public record TradesmanNewSubscriptionPayment(
        EventId eventId,
        ZonedDateTime occurredDate,
        EntityId tradesmanId,
        PaymentMethod paymentMethod,
        Amount amount
) implements ApplicationEvent {

    public TradesmanNewSubscriptionPayment {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(occurredDate);
        Objects.requireNonNull(tradesmanId);
        Objects.requireNonNull(paymentMethod);
        Objects.requireNonNull(amount);
    }

    public static TradesmanNewSubscriptionPayment of(EntityId tradesmanId, PaymentMethod paymentMethod, Amount amount) {
        return new TradesmanNewSubscriptionPayment(EventId.create(), ZonedDateTime.now(), tradesmanId, paymentMethod, amount);
    }

}
