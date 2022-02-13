package org.larrieulacoste.noe.al.trademe.application.event;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;

class TradesmanInvoiceDeletedTest {
    EntityId eventId = EntityId.of("123");

    @Test
    void of() {
        Assertions.assertThat(TradesmanInvoiceDeleted.of(InvoiceEventEntity.of(eventId, null, null, null, null, null)))
                .isNotNull()
                .isInstanceOf(TradesmanInvoiceDeleted.class);
    }

    @Test
    void ofNullEventId() {
        Assertions.assertThatThrownBy(() -> TradesmanInvoiceDeleted.of(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void getEventId() {
        TradesmanInvoiceDeleted tradesmanInvoiceDeleted = TradesmanInvoiceDeleted.of(InvoiceEventEntity.of(eventId, null, null, null, null, null));
        Assertions.assertThat(tradesmanInvoiceDeleted.eventId())
                .isNotNull()
                .isInstanceOf(EventId.class);
    }

    @Test
    void getOccurredDate() {
        TradesmanInvoiceDeleted tradesmanInvoiceDeleted = TradesmanInvoiceDeleted.of(InvoiceEventEntity.of(eventId, null, null, null, null, null));
        Assertions.assertThat(tradesmanInvoiceDeleted.occurredDate().toEpochSecond())
                .isBetween(ZonedDateTime.now().toEpochSecond(), ZonedDateTime.now().toEpochSecond() + 1);
    }

}