package org.larrieulacoste.noe.al.trademe.application.event;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;

class TradesmanInvoiceCreatedTest {
    EntityId eventEntityId = EntityId.of("123");

    @Test
    void of() {
        Assertions.assertThat(TradesmanInvoiceCreated.of(InvoiceEventEntity.of(eventEntityId, null, null, null, null, null)))
                .isNotNull()
                .isInstanceOf(TradesmanInvoiceCreated.class);
    }

    @Test
    void ofNullEventId() {
        Assertions.assertThatThrownBy(() -> TradesmanInvoiceCreated.of(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void getEventId() {
        TradesmanInvoiceCreated tradesmanInvoiceCreated = TradesmanInvoiceCreated.of(InvoiceEventEntity.of(eventEntityId, null, null, null, null, null));
        Assertions.assertThat(tradesmanInvoiceCreated.getEventId())
                .isNotNull()
                .isInstanceOf(EventId.class);
    }

    @Test
    void getOccurredDate() {
        TradesmanInvoiceCreated tradesmanInvoiceCreated = TradesmanInvoiceCreated.of(InvoiceEventEntity.of(eventEntityId, null, null, null, null, null));
        Assertions.assertThat(tradesmanInvoiceCreated.getOccurredDate().toEpochSecond())
                .isBetween(ZonedDateTime.now().toEpochSecond(), ZonedDateTime.now().toEpochSecond() + 1);
    }
}