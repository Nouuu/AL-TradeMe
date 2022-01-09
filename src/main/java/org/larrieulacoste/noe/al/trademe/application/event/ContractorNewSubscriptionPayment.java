package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.domain.model.Amount;
import org.larrieulacoste.noe.al.trademe.domain.model.PaymentMethod;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.Objects;

public final class ContractorNewSubscriptionPayment implements ApplicationEvent {

    public final ContractorEventEntity contractor;
    public final PaymentMethod paymentMethod;
    public final Amount amount;
    private final EventId eventId;
    private final ZonedDateTime occurredDate;

    private ContractorNewSubscriptionPayment(EventId eventId, ZonedDateTime occurredDate, ContractorEventEntity contractor, PaymentMethod paymentMethod, Amount amount) {
        this.eventId = Objects.requireNonNull(eventId);
        this.occurredDate = Objects.requireNonNull(occurredDate);
        this.contractor = Objects.requireNonNull(contractor);
        this.paymentMethod = Objects.requireNonNull(paymentMethod);
        this.amount = Objects.requireNonNull(amount);
    }

    public static ContractorNewSubscriptionPayment of(ContractorEventEntity contractor, PaymentMethod paymentMethod, Amount amount) {
        return new ContractorNewSubscriptionPayment(EventId.create(), ZonedDateTime.now(), contractor, paymentMethod, amount);
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
        return "NewContractorSubscriptionPayment{" +
                "eventId=" + eventId +
                ", occurredDate=" + occurredDate +
                ", contractorEventEntity=" + contractor +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContractorNewSubscriptionPayment that = (ContractorNewSubscriptionPayment) o;

        if (!eventId.equals(that.eventId)) return false;
        if (!occurredDate.equals(that.occurredDate)) return false;
        if (!contractor.equals(that.contractor)) return false;
        return amount.equals(that.amount);
    }

    @Override
    public int hashCode() {
        int result = eventId.hashCode();
        result = 31 * result + occurredDate.hashCode();
        result = 31 * result + contractor.hashCode();
        result = 31 * result + amount.hashCode();
        return result;
    }
}
