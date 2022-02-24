package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.Objects;

public record ContractorSubscriptionPaymentFailure(
        EventId eventId,
        ZonedDateTime occurredDate,
        EntityId contractorId
) implements ApplicationEvent {

    public ContractorSubscriptionPaymentFailure {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(occurredDate);
        Objects.requireNonNull(contractorId);
    }

    public static ContractorSubscriptionPaymentFailure of(EntityId contractorId) {
        return new ContractorSubscriptionPaymentFailure(EventId.create(), ZonedDateTime.now(), contractorId);
    }
}
