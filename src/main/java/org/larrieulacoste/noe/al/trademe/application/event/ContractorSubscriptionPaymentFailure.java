package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.Objects;

public record ContractorSubscriptionPaymentFailure(
        EventId eventId,
        ZonedDateTime occurredDate,
        ContractorEventEntity contractor
) implements ApplicationEvent {

    public ContractorSubscriptionPaymentFailure {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(occurredDate);
        Objects.requireNonNull(contractor);
    }

    public static ContractorSubscriptionPaymentFailure withContractor(ContractorEventEntity tradesman) {
        return new ContractorSubscriptionPaymentFailure(EventId.create(), ZonedDateTime.now(), tradesman);
    }
}
