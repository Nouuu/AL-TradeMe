package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.features.members.infrastructure.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.features.members.infrastructure.kernel.event.EventId;

import java.time.ZonedDateTime;

public final class NewTradesmanRegistration implements ApplicationEvent {
    private final EventId eventId;
    private final ZonedDateTime occurredDate;
    private final TradesmanEventEntity tradesmanEventEntity;

    private NewTradesmanRegistration(EventId eventId, ZonedDateTime occurredDate, TradesmanEventEntity tradesmanEventEntity) {
        this.eventId = eventId;
        this.occurredDate = occurredDate;
        this.tradesmanEventEntity = tradesmanEventEntity;
    }

    public static NewTradesmanRegistration of(TradesmanEventEntity tradesmanEventEntity) {
        return new NewTradesmanRegistration(EventId.create(), ZonedDateTime.now(), tradesmanEventEntity);
    }

    @Override
    public EventId getEventId() {
        return eventId;
    }

    @Override
    public ZonedDateTime getOccurredDate() {
        return occurredDate;
    }

    public TradesmanEventEntity getTradesmanRegistration() {
        return tradesmanEventEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewTradesmanRegistration that = (NewTradesmanRegistration) o;

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
        return "NewTradesmanRegistration{" +
                "eventId=" + eventId +
                ", occurredDate=" + occurredDate +
                ", tradesman=" + tradesmanEventEntity +
                '}';
    }
}
