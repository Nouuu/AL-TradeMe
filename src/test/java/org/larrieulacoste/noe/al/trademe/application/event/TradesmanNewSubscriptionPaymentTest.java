package org.larrieulacoste.noe.al.trademe.application.event;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.larrieulacoste.noe.al.trademe.domain.model.Amount;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;

class TradesmanNewSubscriptionPaymentTest {
    EntityId eventEntityId = EntityId.of("123");

    @Test
    void withTradesmanAndAmount() {
        Assertions.assertThat(TradesmanNewSubscriptionPayment.withTradesmanAndAmount(TradesmanEventEntity.withEntityIdOnly(eventEntityId), Amount.of(0)))
                .isNotNull()
                .isInstanceOf(TradesmanNewSubscriptionPayment.class);
    }

    @Test
    void withTradesmanAndAmountNullArgs() {
        TradesmanEventEntity tradesmanEventEntity = TradesmanEventEntity.withEntityIdOnly(eventEntityId);
        Amount amount = Amount.of(0);
        Assertions.assertThatThrownBy(() -> TradesmanNewSubscriptionPayment.withTradesmanAndAmount(null, amount))
                .isInstanceOf(NullPointerException.class);
        Assertions.assertThatThrownBy(() -> TradesmanNewSubscriptionPayment.withTradesmanAndAmount(tradesmanEventEntity, null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void getEventId() {
        TradesmanNewSubscriptionPayment tradesmanNewSubscriptionPayment =
                TradesmanNewSubscriptionPayment.withTradesmanAndAmount(TradesmanEventEntity.withEntityIdOnly(eventEntityId), Amount.of(0));
        Assertions.assertThat(tradesmanNewSubscriptionPayment.getEventId())
                .isNotNull()
                .isInstanceOf(EventId.class);
    }

    @Test
    void getOccurredDate() {
        TradesmanNewSubscriptionPayment tradesmanNewSubscriptionPayment =
                TradesmanNewSubscriptionPayment.withTradesmanAndAmount(TradesmanEventEntity.withEntityIdOnly(eventEntityId), Amount.of(0));
        Assertions.assertThat(tradesmanNewSubscriptionPayment.getOccurredDate().toEpochSecond())
                .isBetween(ZonedDateTime.now().toEpochSecond(), ZonedDateTime.now().toEpochSecond() + 1);

    }
}