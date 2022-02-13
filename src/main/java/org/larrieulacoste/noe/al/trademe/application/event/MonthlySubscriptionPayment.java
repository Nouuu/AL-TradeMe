package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;

public final class MonthlySubscriptionPayment implements ApplicationEvent {
    private final EventId eventId;
    private final ZonedDateTime occurredDate;

    private MonthlySubscriptionPayment(EventId eventId, ZonedDateTime occurredDate) {
        this.eventId = eventId;
        this.occurredDate = occurredDate;
    }

    public static MonthlySubscriptionPayment create() {
        return new MonthlySubscriptionPayment(EventId.create(), ZonedDateTime.now());
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

        MonthlySubscriptionPayment that = (MonthlySubscriptionPayment) o;

        if (!eventId.equals(that.eventId)) return false;
        return occurredDate.equals(that.occurredDate);
    }

    @Override
    public int hashCode() {
        int result = eventId.hashCode();
        result = 31 * result + occurredDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "MonthlySubscriptionPayment{" +
                "eventId=" + eventId +
                ", occurredDate=" + occurredDate +
                '}';
    }
}
