package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.Objects;

public final class NewContractorPayment implements ApplicationEvent {

    private final EventId eventId;
    private final ZonedDateTime occurredDate;
    private final ContractorEventEntity contractorEventEntity;

    private NewContractorPayment(EventId eventId, ZonedDateTime occurredDate, ContractorEventEntity contractorEventEntity) {
        this.eventId = Objects.requireNonNull(eventId);
        this.occurredDate = Objects.requireNonNull(occurredDate);
        this.contractorEventEntity = Objects.requireNonNull(contractorEventEntity);
    }

    public static NewContractorPayment withContractor(ContractorEventEntity tradesman) {
        return new NewContractorPayment(EventId.create(), ZonedDateTime.now(), tradesman);
    }

    @Override
    public EventId getEventId() {
        return eventId;
    }

    @Override
    public ZonedDateTime getOccurredDate() {
        return occurredDate;
    }

    public ContractorEventEntity getContractor() {
        return contractorEventEntity;
    }


    @Override
    public String toString() {
        return "NewContractorRegistered{" +
                "eventId=" + eventId +
                ", occurredDate=" + occurredDate +
                ", contractor=" + contractorEventEntity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewContractorPayment)) return false;

        NewContractorPayment that = (NewContractorPayment) o;

        if (!eventId.equals(that.eventId)) return false;
        if (!occurredDate.equals(that.occurredDate)) return false;
        return contractorEventEntity.equals(that.contractorEventEntity);
    }

    @Override
    public int hashCode() {
        int result = eventId.hashCode();
        result = 31 * result + occurredDate.hashCode();
        result = 31 * result + contractorEventEntity.hashCode();
        return result;
    }
}
