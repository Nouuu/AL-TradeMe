package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;

public record MonthlySubscriptionPayment(
        EventId eventId,
        ZonedDateTime occurredDate
) implements ApplicationEvent {

    public static MonthlySubscriptionPayment create() {
        return new MonthlySubscriptionPayment(EventId.create(), ZonedDateTime.now());
    }
}
