package org.larrieulacoste.noe.al.trademe.features.member_registration.application;

import org.larrieulacoste.noe.al.trademe.features.member_registration.domain.TradesmanRegistration;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;

public class NewTradesmanRegistration implements ApplicationEvent {
    private final EventId eventId;
    private final ZonedDateTime occurredDate;
    private final TradesmanRegistration tradesman;

    private NewTradesmanRegistration(EventId eventId, ZonedDateTime occurredDate, TradesmanRegistration tradesman) {
        this.eventId = eventId;
        this.occurredDate = occurredDate;
        this.tradesman = tradesman;
    }

    public static NewTradesmanRegistration of(TradesmanRegistration tradesman) {
        return new NewTradesmanRegistration(EventId.create(), ZonedDateTime.now(), tradesman);
    }

    @Override
    public EventId getEventId() {
        return eventId;
    }

    @Override
    public ZonedDateTime getOccurredDate() {
        return occurredDate;
    }

    public TradesmanRegistration getTradesman() {
        return tradesman;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewTradesmanRegistration that = (NewTradesmanRegistration) o;

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
        return "NewTradesmanRegistration{" +
                "eventId=" + eventId +
                ", occurredDate=" + occurredDate +
                ", tradesman=" + tradesman +
                '}';
    }
}
