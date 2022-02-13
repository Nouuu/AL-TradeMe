package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.Objects;

public record ContractorRegistered(
        EventId eventId,
        ZonedDateTime occurredDate,
        ContractorEventEntity contractor
) implements ApplicationEvent {

    public ContractorRegistered {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(occurredDate);
        Objects.requireNonNull(contractor);
    }

    public static ContractorRegistered withContractor(ContractorEventEntity tradesman) {
        return new ContractorRegistered(EventId.create(), ZonedDateTime.now(), tradesman);
    }
}
