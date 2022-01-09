package org.larrieulacoste.noe.al.trademe.application.event;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;

class TradesmanDeletedTest {

    EntityId eventEntityId = EntityId.of("123");

    @Test
    void withTradesman() {
        Assertions.assertThat(TradesmanDeleted.withTradesman(TradesmanEventEntity.withEntityIdOnly(eventEntityId)))
                .isNotNull()
                .isInstanceOf(TradesmanDeleted.class);
    }

    @Test
    void withTradesmanNullArgs() {
        Assertions.assertThatThrownBy(() -> TradesmanDeleted.withTradesman(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void getEventId() {
        TradesmanDeleted tradesmanDeleted = TradesmanDeleted.withTradesman(TradesmanEventEntity.withEntityIdOnly(eventEntityId));
        Assertions.assertThat(tradesmanDeleted.getEventId())
                .isNotNull()
                .isInstanceOf(EventId.class);
    }

    @Test
    void getOccurredDate() {
        TradesmanDeleted tradesmanDeleted = TradesmanDeleted.withTradesman(TradesmanEventEntity.withEntityIdOnly(eventEntityId));
        Assertions.assertThat(tradesmanDeleted.getOccurredDate().toEpochSecond())
                .isBetween(ZonedDateTime.now().toEpochSecond(), ZonedDateTime.now().toEpochSecond() + 1);
    }

}