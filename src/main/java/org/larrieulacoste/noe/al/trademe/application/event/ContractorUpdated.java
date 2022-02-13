package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.Objects;

public record ContractorUpdated(
        EventId eventId,
        ZonedDateTime occurredDate,
        ContractorEventEntity contractor
) implements ApplicationEvent {

    public ContractorUpdated {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(occurredDate);
        Objects.requireNonNull(contractor);
    }

    public static ContractorUpdated withContractor(ContractorEventEntity contractor) {
        return new ContractorUpdated(EventId.create(), ZonedDateTime.now(), contractor);
    }
}
