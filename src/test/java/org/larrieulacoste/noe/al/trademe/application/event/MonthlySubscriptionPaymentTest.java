package org.larrieulacoste.noe.al.trademe.application.event;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class MonthlySubscriptionPaymentTest {
    @Test
    void create() {
        Assertions.assertThat(MonthlySubscriptionPayment.create())
                .isNotNull()
                .isInstanceOf(MonthlySubscriptionPayment.class);
    }

    @Test
    void getEventId() {
        MonthlySubscriptionPayment monthlySubscriptionPayment = MonthlySubscriptionPayment.create();
        Assertions.assertThat(monthlySubscriptionPayment.getEventId())
                .isNotNull()
                .isInstanceOf(EventId.class);
    }

    @Test
    void getOccurredDate() {
        MonthlySubscriptionPayment monthlySubscriptionPayment = MonthlySubscriptionPayment.create();
        Assertions.assertThat(monthlySubscriptionPayment.getOccurredDate().toEpochSecond())
                .isBetween(ZonedDateTime.now().toEpochSecond(), ZonedDateTime.now().toEpochSecond() + 1);
    }
}