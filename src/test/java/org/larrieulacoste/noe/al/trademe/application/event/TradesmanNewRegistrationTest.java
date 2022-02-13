package org.larrieulacoste.noe.al.trademe.application.event;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;

class TradesmanNewRegistrationTest {
    EntityId eventEntityId = EntityId.of("123");

    @Test
    void of() {
        Assertions.assertThat(TradesmanNewRegistration.of(TradesmanEventEntity.withEntityIdOnly(eventEntityId)))
                .isNotNull()
                .isInstanceOf(TradesmanNewRegistration.class);
    }

    @Test
    void ofNullEventId() {
        Assertions.assertThatThrownBy(() -> TradesmanNewRegistration.of(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void getEventId() {
        TradesmanNewRegistration tradesmanInvoiceCreated = TradesmanNewRegistration.of(TradesmanEventEntity.withEntityIdOnly(eventEntityId));
        Assertions.assertThat(tradesmanInvoiceCreated.eventId())
                .isNotNull()
                .isInstanceOf(EventId.class);
    }

    @Test
    void getOccurredDate() {
        TradesmanNewRegistration tradesmanInvoiceCreated = TradesmanNewRegistration.of(TradesmanEventEntity.withEntityIdOnly(eventEntityId));
        Assertions.assertThat(tradesmanInvoiceCreated.occurredDate().toEpochSecond())
                .isBetween(ZonedDateTime.now().toEpochSecond(), ZonedDateTime.now().toEpochSecond() + 1);
    }
}