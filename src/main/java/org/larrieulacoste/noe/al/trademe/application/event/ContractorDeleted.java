package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.Objects;

public final class ContractorDeleted implements ApplicationEvent {

    private final EventId eventId;
    private final ZonedDateTime occurredDate;
    public final ContractorEventEntity contractor;

    private ContractorDeleted(EventId eventId, ZonedDateTime occurredDate, ContractorEventEntity contractor) {
        this.eventId = Objects.requireNonNull(eventId);
        this.occurredDate = Objects.requireNonNull(occurredDate);
        this.contractor = Objects.requireNonNull(contractor);
    }

    public static ContractorDeleted withContractor(ContractorEventEntity contractor) {
        return new ContractorDeleted(EventId.create(), ZonedDateTime.now(), contractor);
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
    public String toString() {
        return "ContractorDeleted{" +
                "eventId=" + eventId +
                ", occurredDate=" + occurredDate +
                ", contractor=" + contractor +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContractorDeleted)) return false;

        ContractorDeleted that = (ContractorDeleted) o;

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
}
