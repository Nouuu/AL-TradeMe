package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.Objects;

public final class NewTradesmanRegistered implements ApplicationEvent {

    private final EventId eventId;
    private final ZonedDateTime occurredDate;
    private final TradesmanEventEntity tradesman;

    private NewTradesmanRegistered(EventId eventId, ZonedDateTime occurredDate, TradesmanEventEntity tradesman) {
        this.eventId = Objects.requireNonNull(eventId);
        this.occurredDate = Objects.requireNonNull(occurredDate);
        this.tradesman = Objects.requireNonNull(tradesman);
    }

    public static NewTradesmanRegistered withTradesman(TradesmanEventEntity tradesman) {
        return new NewTradesmanRegistered(EventId.create(), ZonedDateTime.now(), tradesman);
    }

    @Override
    public EventId getEventId() {
        return eventId;
    }

    @Override
    public ZonedDateTime getOccurredDate() {
        return occurredDate;
    }

    public TradesmanEventEntity getTradesman() {
        return tradesman;
    }


    @Override
    public String toString() {
        return "NewTradesmanApplicative{" +
                "eventId=" + eventId +
                ", occurredDate=" + occurredDate +
                ", tradesman=" + tradesman +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewTradesmanRegistered)) return false;

        NewTradesmanRegistered that = (NewTradesmanRegistered) o;

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
}
