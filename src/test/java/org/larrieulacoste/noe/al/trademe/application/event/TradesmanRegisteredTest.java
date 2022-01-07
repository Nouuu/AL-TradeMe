package org.larrieulacoste.noe.al.trademe.application.event;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;

class TradesmanRegisteredTest {
    EntityId eventEntityId = EntityId.of("123");

    @Test
    void withTradesman() {
        Assertions.assertThat(TradesmanRegistered.withTradesman(TradesmanEventEntity.withEntityIdOnly(eventEntityId)))
                .isNotNull()
                .isInstanceOf(TradesmanRegistered.class);

    }

    @Test
    void withTradesmanNullArgs() {
        Assertions.assertThatThrownBy(() -> TradesmanRegistered.withTradesman(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void getEventId() {
        TradesmanRegistered tradesmanRegistered = TradesmanRegistered.withTradesman(TradesmanEventEntity.withEntityIdOnly(eventEntityId));
        Assertions.assertThat(tradesmanRegistered.getEventId())
                .isNotNull()
                .isInstanceOf(EventId.class);
    }

    @Test
    void getOccurredDate() {
        TradesmanRegistered tradesmanRegistered = TradesmanRegistered.withTradesman(TradesmanEventEntity.withEntityIdOnly(eventEntityId));
        Assertions.assertThat(tradesmanRegistered.getOccurredDate().toEpochSecond())
                .isBetween(ZonedDateTime.now().toEpochSecond(), ZonedDateTime.now().toEpochSecond() + 1);
    }

}