package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

public final class ContractorsSubscriptionPendingPayment implements ApplicationEvent {
    private final EventId eventId;
    private final ZonedDateTime occurredDate;
    private final List<ContractorEventEntity> contractors;

    private ContractorsSubscriptionPendingPayment(EventId eventId, ZonedDateTime occurredDate, List<ContractorEventEntity> contractors) {
        this.eventId = Objects.requireNonNull(eventId);
        this.occurredDate = Objects.requireNonNull(occurredDate);
        this.contractors = Objects.requireNonNull(contractors);
    }

    public static ContractorsSubscriptionPendingPayment withContractors(List<ContractorEventEntity> contractors) {
        return new ContractorsSubscriptionPendingPayment(EventId.create(), ZonedDateTime.now(), contractors);
    }

    @Override
    public EventId getEventId() {
        return eventId;
    }

    @Override
    public ZonedDateTime getOccurredDate() {
        return occurredDate;
    }

    public List<ContractorEventEntity> getContractors() {
        return List.copyOf(contractors);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContractorsSubscriptionPendingPayment that = (ContractorsSubscriptionPendingPayment) o;

        if (!eventId.equals(that.eventId)) return false;
        if (!occurredDate.equals(that.occurredDate)) return false;
        return contractors.equals(that.contractors);
    }

    @Override
    public int hashCode() {
        int result = eventId.hashCode();
        result = 31 * result + occurredDate.hashCode();
        result = 31 * result + contractors.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ContractorsSubscriptionPendingPayment{" +
                "eventId=" + eventId +
                ", occurredDate=" + occurredDate +
                ", contractors=" + contractors +
                '}';
    }
}
