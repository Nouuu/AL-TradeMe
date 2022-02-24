package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.Objects;

public record ContractorInvoiceDeleted(
        EventId eventId,
        ZonedDateTime occurredDate,
        EntityId invoiceId,
        EntityId memberId
) implements ApplicationEvent {

    public ContractorInvoiceDeleted {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(occurredDate);
        Objects.requireNonNull(invoiceId);
        Objects.requireNonNull(memberId);
    }

    public static ContractorInvoiceDeleted of(
            EntityId invoiceId,
            EntityId memberId
    ) {
        return new ContractorInvoiceDeleted(
                EventId.create(),
                ZonedDateTime.now(),
                invoiceId,
                memberId
        );
    }
}
