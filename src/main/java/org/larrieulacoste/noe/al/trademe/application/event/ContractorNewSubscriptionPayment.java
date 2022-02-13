package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.domain.model.Amount;
import org.larrieulacoste.noe.al.trademe.domain.model.PaymentMethod;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.Objects;

public record ContractorNewSubscriptionPayment(
        EventId eventId,
        ZonedDateTime occurredDate,
        ContractorEventEntity contractor,
        PaymentMethod paymentMethod,
        Amount amount
) implements ApplicationEvent {

    public ContractorNewSubscriptionPayment {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(occurredDate);
        Objects.requireNonNull(contractor);
        Objects.requireNonNull(paymentMethod);
        Objects.requireNonNull(amount);
    }

    public static ContractorNewSubscriptionPayment of(ContractorEventEntity contractor, PaymentMethod paymentMethod, Amount amount) {
        return new ContractorNewSubscriptionPayment(EventId.create(), ZonedDateTime.now(), contractor, paymentMethod, amount);
    }
}
