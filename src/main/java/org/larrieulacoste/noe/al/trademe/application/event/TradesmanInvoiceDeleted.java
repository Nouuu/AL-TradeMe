package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.Objects;

public record TradesmanInvoiceDeleted(
        EventId eventId,
        ZonedDateTime occurredDate,
        InvoiceEventEntity invoice
) implements ApplicationEvent {

    public TradesmanInvoiceDeleted {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(occurredDate);
        Objects.requireNonNull(invoice);
    }

    public static TradesmanInvoiceDeleted of(InvoiceEventEntity invoiceEventEntity) {
        return new TradesmanInvoiceDeleted(EventId.create(), ZonedDateTime.now(), invoiceEventEntity);
    }

}
