package org.larrieulacoste.noe.al.trademe.application.event;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;

class TradesmanUpdatedTest {
    EntityId eventEntityId = EntityId.of("123");


    @Test
    void withTradesman() {
        Assertions.assertThat(TradesmanUpdated.withTradesman(TradesmanEventEntity.withEntityIdOnly(eventEntityId)))
                .isNotNull()
                .isInstanceOf(TradesmanUpdated.class);

    }

    @Test
    void withTradesmanNullArgs() {
        Assertions.assertThatThrownBy(() -> TradesmanUpdated.withTradesman(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void getEventId() {
        TradesmanUpdated tradesmanUpdated = TradesmanUpdated.withTradesman(TradesmanEventEntity.withEntityIdOnly(eventEntityId));
        Assertions.assertThat(tradesmanUpdated.getEventId())
                .isNotNull()
                .isInstanceOf(EventId.class);
    }

    @Test
    void getOccurredDate() {
        TradesmanUpdated tradesmanUpdated = TradesmanUpdated.withTradesman(TradesmanEventEntity.withEntityIdOnly(eventEntityId));
        Assertions.assertThat(tradesmanUpdated.getOccurredDate().toEpochSecond())
                .isBetween(ZonedDateTime.now().toEpochSecond(), ZonedDateTime.now().toEpochSecond() + 1);
    }
}