package org.larrieulacoste.noe.al.trademe.domain.event;

import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.EntityId;

import java.time.ZonedDateTime;
import java.util.Objects;

public record ContractorDeleted(
        EventId eventId,
        ZonedDateTime occurredDate,
        EntityId contractorId
) implements ApplicationEvent {

    public ContractorDeleted {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(occurredDate);
        Objects.requireNonNull(contractorId);
    }

    public static ContractorDeleted of(EntityId contractorId) {
        return new ContractorDeleted(EventId.create(), ZonedDateTime.now(), contractorId);
    }
}
