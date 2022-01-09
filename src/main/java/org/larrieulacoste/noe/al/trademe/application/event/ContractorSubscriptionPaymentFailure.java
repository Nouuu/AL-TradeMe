package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.Objects;

public final class ContractorSubscriptionPaymentFailure implements ApplicationEvent {

    public final ContractorEventEntity contractor;
    private final EventId eventId;
    private final ZonedDateTime occurredDate;

    private ContractorSubscriptionPaymentFailure(EventId eventId, ZonedDateTime occurredDate, ContractorEventEntity contractor) {
        this.eventId = Objects.requireNonNull(eventId);
        this.occurredDate = Objects.requireNonNull(occurredDate);
        this.contractor = Objects.requireNonNull(contractor);
    }

    public static ContractorSubscriptionPaymentFailure withContractor(ContractorEventEntity tradesman) {
        return new ContractorSubscriptionPaymentFailure(EventId.create(), ZonedDateTime.now(), tradesman);
    }

    @Override
    public EventId getEventId() {
        return eventId;
    }

    @Override
    public ZonedDateTime getOccurredDate() {
        return occurredDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContractorSubscriptionPaymentFailure that = (ContractorSubscriptionPaymentFailure) o;

        if (!eventId.equals(that.eventId)) return false;
        if (!occurredDate.equals(that.occurredDate)) return false;
        return contractor.equals(that.contractor);
    }

    @Override
    public int hashCode() {
        int result = eventId.hashCode();
        result = 31 * result + occurredDate.hashCode();
        result = 31 * result + contractor.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ContractorSubscriptionPaymentFailure{" +
                "eventId=" + eventId +
                ", occurredDate=" + occurredDate +
                ", contractorEventEntity=" + contractor +
                '}';
    }
}
