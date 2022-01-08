package org.larrieulacoste.noe.al.trademe.application.event;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;

class ContractorInvoiceDeletedTest {

    EntityId eventId = EntityId.of("123");

    @Test
    void of() {
        Assertions.assertThat(ContractorInvoiceDeleted.of(InvoiceEventEntity.of(eventId, null, null, null, null, null)))
                .isNotNull()
                .isInstanceOf(ContractorInvoiceDeleted.class);
    }

    @Test
    void ofNullEventId() {
        Assertions.assertThatThrownBy(() -> ContractorInvoiceDeleted.of(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void getEventId() {
        ContractorInvoiceDeleted contractorInvoiceDeleted = ContractorInvoiceDeleted.of(InvoiceEventEntity.of(eventId, null, null, null, null, null));
        Assertions.assertThat(contractorInvoiceDeleted.getEventId())
                .isNotNull()
                .isInstanceOf(EventId.class);
    }

    @Test
    void getOccurredDate() {
        ContractorInvoiceDeleted contractorInvoiceDeleted = ContractorInvoiceDeleted.of(InvoiceEventEntity.of(eventId, null, null, null, null, null));
        Assertions.assertThat(contractorInvoiceDeleted.getOccurredDate().toEpochSecond())
                .isBetween(ZonedDateTime.now().toEpochSecond(), ZonedDateTime.now().toEpochSecond() + 1);
    }
}