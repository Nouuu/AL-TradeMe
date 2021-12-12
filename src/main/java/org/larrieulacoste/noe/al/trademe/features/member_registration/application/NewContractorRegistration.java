package org.larrieulacoste.noe.al.trademe.features.member_registration.application;

import org.larrieulacoste.noe.al.trademe.features.member_registration.domain.ContractorRegistration;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.Objects;

public class NewContractorRegistration implements ApplicationEvent {

    private final EventId eventId;
    private final ZonedDateTime occurredDate;
    private final ContractorRegistration contractor;

    private NewContractorRegistration(EventId eventId, ZonedDateTime occurredDate, ContractorRegistration contractor) {
        this.eventId = Objects.requireNonNull(eventId);
        this.occurredDate = Objects.requireNonNull(occurredDate);
        this.contractor = Objects.requireNonNull(contractor);
    }

    public static NewContractorRegistration of(ContractorRegistration contractor) {
        return new NewContractorRegistration(EventId.create(), ZonedDateTime.now(), contractor);
    }

    @Override
    public EventId getEventId() {
        return eventId;
    }

    @Override
    public ZonedDateTime getOccurredDate() {
        return occurredDate;
    }

    public ContractorRegistration getContractor() {
        return contractor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewContractorRegistration that = (NewContractorRegistration) o;

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
