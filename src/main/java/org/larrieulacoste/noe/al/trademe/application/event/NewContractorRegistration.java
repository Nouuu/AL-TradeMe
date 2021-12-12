package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.domain.model.ContractorRegistration;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.Objects;

public final class NewContractorRegistration implements ApplicationEvent {

    private final EventId eventId;
    private final ZonedDateTime occurredDate;
    private final ContractorRegistration contractorRegistration;

    private NewContractorRegistration(EventId eventId, ZonedDateTime occurredDate, ContractorRegistration contractorRegistration) {
        this.eventId = Objects.requireNonNull(eventId);
        this.occurredDate = Objects.requireNonNull(occurredDate);
        this.contractorRegistration = Objects.requireNonNull(contractorRegistration);
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

    public ContractorRegistration getContractorRegistration() {
        return contractorRegistration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewContractorRegistration that = (NewContractorRegistration) o;

        if (!eventId.equals(that.eventId)) return false;
        if (!occurredDate.equals(that.occurredDate)) return false;
        return contractorRegistration.equals(that.contractorRegistration);
    }

    @Override
    public int hashCode() {
        int result = eventId.hashCode();
        result = 31 * result + occurredDate.hashCode();
        result = 31 * result + contractorRegistration.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "NewContractorRegistration{" +
                "eventId=" + eventId +
                ", occurredDate=" + occurredDate +
                ", contractor=" + contractorRegistration +
                '}';
    }
}
