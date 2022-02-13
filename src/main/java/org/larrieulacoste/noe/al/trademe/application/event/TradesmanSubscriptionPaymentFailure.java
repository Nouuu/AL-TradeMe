package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.Objects;

public final class TradesmanSubscriptionPaymentFailure implements ApplicationEvent {

    public final TradesmanEventEntity tradesman;
    private final EventId eventId;
    private final ZonedDateTime occurredDate;

    private TradesmanSubscriptionPaymentFailure(EventId eventId, ZonedDateTime occurredDate, TradesmanEventEntity tradesman) {
        this.eventId = Objects.requireNonNull(eventId);
        this.occurredDate = Objects.requireNonNull(occurredDate);
        this.tradesman = Objects.requireNonNull(tradesman);
    }

    public static TradesmanSubscriptionPaymentFailure withTradesman(TradesmanEventEntity tradesman) {
        return new TradesmanSubscriptionPaymentFailure(EventId.create(), ZonedDateTime.now(), tradesman);
    }

    @Override
    public EventId eventId() {
        return eventId;
    }

    @Override
    public ZonedDateTime occurredDate() {
        return occurredDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TradesmanSubscriptionPaymentFailure that = (TradesmanSubscriptionPaymentFailure) o;

        if (!eventId.equals(that.eventId)) return false;
        if (!occurredDate.equals(that.occurredDate)) return false;
        return tradesman.equals(that.tradesman);
    }

    @Override
    public int hashCode() {
        int result = eventId.hashCode();
        result = 31 * result + occurredDate.hashCode();
        result = 31 * result + tradesman.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TradesmanSubscriptionPaymentFailure{" +
                "eventId=" + eventId +
                ", occurredDate=" + occurredDate +
                ", tradesmanEventEntity=" + tradesman +
                '}';
    }
}
