package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.domain.model.Amount;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.Objects;

public final class TradesmanNewSubscriptionPayment implements ApplicationEvent {

    private final EventId eventId;
    private final ZonedDateTime occurredDate;
    public final TradesmanEventEntity tradesman;
    public final Amount amount;

    private TradesmanNewSubscriptionPayment(EventId eventId, ZonedDateTime occurredDate, TradesmanEventEntity tradesman, Amount amount) {
        this.eventId = Objects.requireNonNull(eventId);
        this.occurredDate = Objects.requireNonNull(occurredDate);
        this.tradesman = Objects.requireNonNull(tradesman);
        this.amount = Objects.requireNonNull(amount);
    }

    public static TradesmanNewSubscriptionPayment withTradesmanAndAmount(TradesmanEventEntity tradesman, Amount amount) {
        return new TradesmanNewSubscriptionPayment(EventId.create(), ZonedDateTime.now(), tradesman, amount);
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

        TradesmanNewSubscriptionPayment that = (TradesmanNewSubscriptionPayment) o;

        if (!eventId.equals(that.eventId)) return false;
        if (!occurredDate.equals(that.occurredDate)) return false;
        if (!tradesman.equals(that.tradesman)) return false;
        return amount.equals(that.amount);
    }

    @Override
    public int hashCode() {
        int result = eventId.hashCode();
        result = 31 * result + occurredDate.hashCode();
        result = 31 * result + tradesman.hashCode();
        result = 31 * result + amount.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "NewTradesmanSubscriptionPayment{" +
                "eventId=" + eventId +
                ", occurredDate=" + occurredDate +
                ", tradesman=" + tradesman +
                ", amount=" + amount +
                '}';
    }
}
