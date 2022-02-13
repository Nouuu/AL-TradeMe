package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.Objects;

public final class TradesmanInvoiceCreated implements ApplicationEvent {

    public final InvoiceEventEntity invoice;
    private final EventId eventId;
    private final ZonedDateTime occurredDate;

    private TradesmanInvoiceCreated(EventId eventId, ZonedDateTime occurredDate, InvoiceEventEntity invoice) {
        this.eventId = Objects.requireNonNull(eventId);
        this.occurredDate = Objects.requireNonNull(occurredDate);
        this.invoice = Objects.requireNonNull(invoice);
    }

    public static TradesmanInvoiceCreated of(InvoiceEventEntity invoiceEventEntity) {
        return new TradesmanInvoiceCreated(EventId.create(), ZonedDateTime.now(), invoiceEventEntity);
    }

    @Override
    public EventId eventId() {
        return eventId;
    }

    @Override
    public ZonedDateTime occurredDate() {
        return occurredDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TradesmanInvoiceCreated that = (TradesmanInvoiceCreated) o;

        if (!eventId.equals(that.eventId)) return false;
        if (!occurredDate.equals(that.occurredDate)) return false;
        return invoice.equals(that.invoice);
    }

    @Override
    public int hashCode() {
        int result = eventId.hashCode();
        result = 31 * result + occurredDate.hashCode();
        result = 31 * result + invoice.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TradesmanInvoiceCreated{" +
                "eventId=" + eventId +
                ", occurredDate=" + occurredDate +
                ", invoiceEventEntity=" + invoice +
                '}';
    }
}
