package org.larrieulacoste.noe.al.trademe.domain.event;

import org.larrieulacoste.noe.al.trademe.shared_kernel.model.EntityId;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.Objects;

public record TradesmanInvoiceDeleted(
        EventId eventId,
        ZonedDateTime occurredDate,
        EntityId invoiceId,
        EntityId memberId
) implements ApplicationEvent {

    public TradesmanInvoiceDeleted {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(occurredDate);
        Objects.requireNonNull(invoiceId);
        Objects.requireNonNull(memberId);
    }

    public static TradesmanInvoiceDeleted of(
            EntityId invoiceId,
            EntityId memberId
    ) {
        return new TradesmanInvoiceDeleted(
                EventId.create(),
                ZonedDateTime.now(),
                invoiceId,
                memberId
        );
    }

}
