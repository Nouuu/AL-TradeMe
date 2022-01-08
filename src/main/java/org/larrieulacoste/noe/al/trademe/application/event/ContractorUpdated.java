package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.Objects;

public final class ContractorUpdated implements ApplicationEvent {

    public final ContractorEventEntity contractor;
    private final EventId eventId;
    private final ZonedDateTime occurredDate;

    private ContractorUpdated(EventId eventId, ZonedDateTime occurredDate, ContractorEventEntity contractor) {
        this.eventId = Objects.requireNonNull(eventId);
        this.occurredDate = Objects.requireNonNull(occurredDate);
        this.contractor = Objects.requireNonNull(contractor);
    }

    public static ContractorUpdated withContractor(ContractorEventEntity contractor) {
        return new ContractorUpdated(EventId.create(), ZonedDateTime.now(), contractor);
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
        return "NewContractorRegistered{" +
                "eventId=" + eventId +
                ", occurredDate=" + occurredDate +
                ", contractor=" + contractor +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContractorUpdated)) return false;

        ContractorUpdated that = (ContractorUpdated) o;

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
