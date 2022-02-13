package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.Objects;

public record ContractorNewRegistration(
        EventId eventId,
        ZonedDateTime occurredDate,
        ContractorEventEntity contractor
) implements ApplicationEvent {

    public ContractorNewRegistration {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(occurredDate);
        Objects.requireNonNull(contractor);
    }

    public static ContractorNewRegistration of(ContractorEventEntity contractorEventEntity) {
        return new ContractorNewRegistration(EventId.create(), ZonedDateTime.now(), contractorEventEntity);
    }
}
