package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.domain.entity.Tradesman;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.Objects;

public final class NewTradesmanApplicative implements ApplicationEvent {

    private final EventId eventId;
    private final ZonedDateTime occurredDate;
    private final Tradesman tradesman;
    private final Double amount;

    private NewTradesmanApplicative(EventId eventId, ZonedDateTime occurredDate, Tradesman tradesman, Double amount) {
        this.eventId = Objects.requireNonNull(eventId);
        this.occurredDate = Objects.requireNonNull(occurredDate);
        this.tradesman = Objects.requireNonNull(tradesman);
        this.amount = amount;
    }

    public static NewTradesmanApplicative withTradesmanAndAmount(Tradesman tradesman, Double amount) {
        return new NewTradesmanApplicative(EventId.create(), ZonedDateTime.now(), tradesman, amount);
    }

    @Override
    public EventId getEventId() {
        return eventId;
    }

    @Override
    public ZonedDateTime getOccurredDate() {
        return occurredDate;
    }

    public Tradesman getTradesman() {
        return tradesman;
    }

    public Double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "NewTradesmanApplicative{" +
                "eventId=" + eventId +
                ", occurredDate=" + occurredDate +
                ", tradesman=" + tradesman +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewTradesmanApplicative)) return false;

        NewTradesmanApplicative that = (NewTradesmanApplicative) o;

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
}
