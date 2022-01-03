package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.Objects;

public final class TradesmanSubscriptionPaymentFailure implements ApplicationEvent {

    private final EventId eventId;
    private final ZonedDateTime occurredDate;
    private final TradesmanEventEntity tradesmanEventEntity;

    private TradesmanSubscriptionPaymentFailure(EventId eventId, ZonedDateTime occurredDate, TradesmanEventEntity tradesmanEventEntity) {
        this.eventId = Objects.requireNonNull(eventId);
        this.occurredDate = Objects.requireNonNull(occurredDate);
        this.tradesmanEventEntity = Objects.requireNonNull(tradesmanEventEntity);
    }

    public static TradesmanSubscriptionPaymentFailure withTradesman(TradesmanEventEntity tradesman) {
        return new TradesmanSubscriptionPaymentFailure(EventId.create(), ZonedDateTime.now(), tradesman);
    }

    @Override
    public EventId getEventId() {
        return eventId;
    }

    @Override
    public ZonedDateTime getOccurredDate() {
        return occurredDate;
    }

    public TradesmanEventEntity getTradesmanEventEntity() {
        return tradesmanEventEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TradesmanSubscriptionPaymentFailure that = (TradesmanSubscriptionPaymentFailure) o;

        if (!eventId.equals(that.eventId)) return false;
        if (!occurredDate.equals(that.occurredDate)) return false;
        return tradesmanEventEntity.equals(that.tradesmanEventEntity);
    }

    @Override
    public int hashCode() {
        int result = eventId.hashCode();
        result = 31 * result + occurredDate.hashCode();
        result = 31 * result + tradesmanEventEntity.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TradesmanSubscriptionPaymentFailure{" +
                "eventId=" + eventId +
                ", occurredDate=" + occurredDate +
                ", tradesmanEventEntity=" + tradesmanEventEntity +
                '}';
    }
}