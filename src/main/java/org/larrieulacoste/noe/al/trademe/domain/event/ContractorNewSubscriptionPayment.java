package org.larrieulacoste.noe.al.trademe.domain.event;

import org.larrieulacoste.noe.al.trademe.shared_kernel.model.Amount;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.EntityId;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.PaymentMethod;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.Objects;

public record ContractorNewSubscriptionPayment(
        EventId eventId,
        ZonedDateTime occurredDate,
        EntityId contractorId,
        PaymentMethod paymentMethod,
        Amount amount
) implements ApplicationEvent {

    public ContractorNewSubscriptionPayment {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(occurredDate);
        Objects.requireNonNull(contractorId);
        Objects.requireNonNull(paymentMethod);
        Objects.requireNonNull(amount);
    }

    public static ContractorNewSubscriptionPayment of(EntityId contractorId, PaymentMethod paymentMethod, Amount amount) {
        return new ContractorNewSubscriptionPayment(EventId.create(), ZonedDateTime.now(), contractorId, paymentMethod, amount);
    }
}
