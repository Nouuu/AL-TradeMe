package org.larrieulacoste.noe.al.trademe.application.event;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TradesmanSubscriptionPaymentFailureTest {
    EntityId eventEntityId = EntityId.of("123");
    TradesmanEventEntity tradesmanEventEntity = TradesmanEventEntity.withEntityIdOnly(eventEntityId);

    @Test
    void withTradesman() {
        Assertions.assertThat(TradesmanSubscriptionPaymentFailure.withTradesman(tradesmanEventEntity))
                .isNotNull()
                .isInstanceOf(TradesmanSubscriptionPaymentFailure.class);
    }

    @Test
    void withNullTradesman() {
        Assertions.assertThatThrownBy(() -> TradesmanSubscriptionPaymentFailure.withTradesman(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void getEventId() {
        TradesmanSubscriptionPaymentFailure tradesmanSubscriptionPaymentFailure =
                TradesmanSubscriptionPaymentFailure.withTradesman(tradesmanEventEntity);
        Assertions.assertThat(tradesmanSubscriptionPaymentFailure.getEventId())
                .isNotNull()
                .isInstanceOf(EventId.class);
    }

    @Test
    void getOccurredDate() {
        TradesmanSubscriptionPaymentFailure tradesmanSubscriptionPaymentFailure =
                TradesmanSubscriptionPaymentFailure.withTradesman(tradesmanEventEntity);
        Assertions.assertThat(tradesmanSubscriptionPaymentFailure.getOccurredDate().toEpochSecond())
                .isBetween(ZonedDateTime.now().toEpochSecond(), ZonedDateTime.now().toEpochSecond() + 1);
    }
}