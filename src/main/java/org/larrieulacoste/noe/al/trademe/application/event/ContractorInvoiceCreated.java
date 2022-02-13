package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.Objects;

public record ContractorInvoiceCreated(
        EventId eventId,
        ZonedDateTime occurredDate,
        InvoiceEventEntity invoice
) implements ApplicationEvent {

    public ContractorInvoiceCreated {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(occurredDate);
        Objects.requireNonNull(invoice);
    }

    public static ContractorInvoiceCreated of(InvoiceEventEntity invoiceEventEntity) {
        return new ContractorInvoiceCreated(EventId.create(), ZonedDateTime.now(), invoiceEventEntity);
    }
}
