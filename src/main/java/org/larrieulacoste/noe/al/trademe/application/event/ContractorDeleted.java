package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.Objects;

public record ContractorDeleted(
        EventId eventId,
        ZonedDateTime occurredDate,
        ContractorEventEntity contractor
) implements ApplicationEvent {

    public ContractorDeleted {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(occurredDate);
        Objects.requireNonNull(contractor);
    }

    public static ContractorDeleted withContractor(ContractorEventEntity contractor) {
        return new ContractorDeleted(EventId.create(), ZonedDateTime.now(), contractor);
    }
}
