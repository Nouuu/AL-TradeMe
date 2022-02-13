package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.domain.model.Amount;
import org.larrieulacoste.noe.al.trademe.domain.model.PaymentMethod;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.Objects;

public record TradesmanNewSubscriptionPayment(
        EventId eventId,
        ZonedDateTime occurredDate,
        TradesmanEventEntity tradesman,
        PaymentMethod paymentMethod,
        Amount amount
) implements ApplicationEvent {

    public TradesmanNewSubscriptionPayment {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(occurredDate);
        Objects.requireNonNull(tradesman);
        Objects.requireNonNull(paymentMethod);
        Objects.requireNonNull(amount);
    }

    public static TradesmanNewSubscriptionPayment of(TradesmanEventEntity tradesman, PaymentMethod paymentMethod, Amount amount) {
        return new TradesmanNewSubscriptionPayment(EventId.create(), ZonedDateTime.now(), tradesman, paymentMethod, amount);
    }

}
