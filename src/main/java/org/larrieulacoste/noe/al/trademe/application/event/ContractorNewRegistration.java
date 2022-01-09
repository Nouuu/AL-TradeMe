package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.Objects;

public final class ContractorNewRegistration implements ApplicationEvent {

    public final ContractorEventEntity contractor;
    private final EventId eventId;
    private final ZonedDateTime occurredDate;

    private ContractorNewRegistration(EventId eventId, ZonedDateTime occurredDate, ContractorEventEntity contractor) {
        this.eventId = Objects.requireNonNull(eventId);
        this.occurredDate = Objects.requireNonNull(occurredDate);
        this.contractor = Objects.requireNonNull(contractor);
    }

    public static ContractorNewRegistration of(ContractorEventEntity contractorEventEntity) {
        return new ContractorNewRegistration(EventId.create(), ZonedDateTime.now(), contractorEventEntity);
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

        ContractorNewRegistration that = (ContractorNewRegistration) o;

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
        return "NewContractorRegistration{" +
                "eventId=" + eventId +
                ", occurredDate=" + occurredDate +
                ", contractor=" + contractor +
                '}';
    }
}
