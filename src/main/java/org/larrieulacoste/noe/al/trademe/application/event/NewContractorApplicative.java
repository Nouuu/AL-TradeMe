package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.Objects;

public final class NewContractorApplicative implements ApplicationEvent {

    private final EventId eventId;
    private final ZonedDateTime occurredDate;
    private final ContractorEventEntity contractorEventEntity;
    private final Double amount;

    private NewContractorApplicative(EventId eventId, ZonedDateTime occurredDate, ContractorEventEntity contractorEventEntity, Double amount) {
        this.eventId = Objects.requireNonNull(eventId);
        this.occurredDate = Objects.requireNonNull(occurredDate);
        this.contractorEventEntity = Objects.requireNonNull(contractorEventEntity);
        this.amount = amount;
    }

    public static NewContractorApplicative withContractorAndAmount(ContractorEventEntity tradesman, Double amount) {
        return new NewContractorApplicative(EventId.create(), ZonedDateTime.now(), tradesman, amount);
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

    public Double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "NewContractorApplicative{" +
                "eventId=" + eventId +
                ", occurredDate=" + occurredDate +
                ", contractor=" + contractorEventEntity +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewContractorApplicative)) return false;

        NewContractorApplicative that = (NewContractorApplicative) o;

        if (!eventId.equals(that.eventId)) return false;
        if (!occurredDate.equals(that.occurredDate)) return false;
        if (!contractorEventEntity.equals(that.contractorEventEntity)) return false;
        return amount.equals(that.amount);
    }

    @Override
    public int hashCode() {
        int result = eventId.hashCode();
        result = 31 * result + occurredDate.hashCode();
        result = 31 * result + contractorEventEntity.hashCode();
        result = 31 * result + amount.hashCode();
        return result;
    }
}