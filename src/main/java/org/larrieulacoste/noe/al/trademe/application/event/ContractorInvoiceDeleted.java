package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.Objects;

public record ContractorInvoiceDeleted(
        EventId eventId,
        ZonedDateTime occurredDate,
        InvoiceEventEntity invoice
) implements ApplicationEvent {

    public ContractorInvoiceDeleted {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(occurredDate);
        Objects.requireNonNull(invoice);
    }

    public static ContractorInvoiceDeleted of(InvoiceEventEntity invoiceEventEntity) {
        return new ContractorInvoiceDeleted(EventId.create(), ZonedDateTime.now(), invoiceEventEntity);
    }
}
