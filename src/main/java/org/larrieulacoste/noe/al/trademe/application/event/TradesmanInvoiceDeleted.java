package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.Objects;

public final class TradesmanInvoiceDeleted implements ApplicationEvent {

    private final EventId eventId;
    private final ZonedDateTime occurredDate;
    private final InvoiceEventEntity invoiceEventEntity;

    private TradesmanInvoiceDeleted(EventId eventId, ZonedDateTime occurredDate, InvoiceEventEntity invoiceEventEntity) {
        this.eventId = Objects.requireNonNull(eventId);
        this.occurredDate = Objects.requireNonNull(occurredDate);
        this.invoiceEventEntity = Objects.requireNonNull(invoiceEventEntity);
    }

    public static TradesmanInvoiceDeleted of(InvoiceEventEntity invoiceEventEntity) {
        return new TradesmanInvoiceDeleted(EventId.create(), ZonedDateTime.now(), invoiceEventEntity);
    }

    @Override
    public EventId getEventId() {
        return eventId;
    }

    @Override
    public ZonedDateTime getOccurredDate() {
        return occurredDate;
    }

    public InvoiceEventEntity getInvoice() {
        return invoiceEventEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TradesmanInvoiceDeleted that = (TradesmanInvoiceDeleted) o;

        if (!eventId.equals(that.eventId)) return false;
        if (!occurredDate.equals(that.occurredDate)) return false;
        return invoiceEventEntity.equals(that.invoiceEventEntity);
    }

    @Override
    public int hashCode() {
        int result = eventId.hashCode();
        result = 31 * result + occurredDate.hashCode();
        result = 31 * result + invoiceEventEntity.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TradesmanInvoiceDeleted{" +
                "eventId=" + eventId +
                ", occurredDate=" + occurredDate +
                ", invoiceEventEntity=" + invoiceEventEntity +
                '}';
    }
}
