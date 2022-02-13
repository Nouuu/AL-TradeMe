package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

public record ContractorsSubscriptionPendingPayment(
        EventId eventId,
        ZonedDateTime occurredDate,
        List<ContractorEventEntity> contractors
) implements ApplicationEvent {
    public ContractorsSubscriptionPendingPayment {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(occurredDate);
        Objects.requireNonNull(contractors);
    }

    public static ContractorsSubscriptionPendingPayment withContractors(List<ContractorEventEntity> contractors) {
        return new ContractorsSubscriptionPendingPayment(EventId.create(), ZonedDateTime.now(), contractors);
    }

    @Override
    public List<ContractorEventEntity> contractors() {
        return List.copyOf(contractors);
    }
}
