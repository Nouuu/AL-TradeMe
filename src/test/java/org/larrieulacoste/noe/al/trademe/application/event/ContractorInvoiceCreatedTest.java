package org.larrieulacoste.noe.al.trademe.application.event;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;

class ContractorInvoiceCreatedTest {
    EntityId eventEntityId = EntityId.of("123");

    @Test
    void of() {
        Assertions.assertThat(ContractorInvoiceCreated.of(InvoiceEventEntity.of(eventEntityId, null, null, null, null)))
                .isNotNull()
                .isInstanceOf(ContractorInvoiceCreated.class);
    }

    @Test
    void ofNullEventId() {
        Assertions.assertThatThrownBy(() -> ContractorInvoiceCreated.of(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void getEventId() {
        ContractorInvoiceCreated contractorInvoiceCreated = ContractorInvoiceCreated.of(InvoiceEventEntity.of(eventEntityId, null, null, null, null));
        Assertions.assertThat(contractorInvoiceCreated.getEventId())
                .isNotNull()
                .isInstanceOf(EventId.class);
    }

    @Test
    void getOccurredDate() {
        ContractorInvoiceCreated contractorInvoiceCreated = ContractorInvoiceCreated.of(InvoiceEventEntity.of(eventEntityId, null, null, null, null));
        Assertions.assertThat(contractorInvoiceCreated.getOccurredDate().toEpochSecond())
                .isBetween(ZonedDateTime.now().toEpochSecond(), ZonedDateTime.now().toEpochSecond() + 1);
    }
}