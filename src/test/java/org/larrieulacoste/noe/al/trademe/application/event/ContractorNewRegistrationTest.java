package org.larrieulacoste.noe.al.trademe.application.event;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;

class ContractorNewRegistrationTest {
    EntityId eventEntityId = EntityId.of("123");

    @Test
    void of() {
        Assertions.assertThat(ContractorNewRegistration.of(ContractorEventEntity.withEntityIdOnly(eventEntityId)))
                .isNotNull()
                .isInstanceOf(ContractorNewRegistration.class);
    }

    @Test
    void ofNullEventId() {
        Assertions.assertThatThrownBy(() -> ContractorNewRegistration.of(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void getEventId() {
        ContractorNewRegistration contractorInvoiceCreated = ContractorNewRegistration.of(ContractorEventEntity.withEntityIdOnly(eventEntityId));
        Assertions.assertThat(contractorInvoiceCreated.eventId())
                .isNotNull()
                .isInstanceOf(EventId.class);
    }

    @Test
    void getOccurredDate() {
        ContractorNewRegistration contractorInvoiceCreated = ContractorNewRegistration.of(ContractorEventEntity.withEntityIdOnly(eventEntityId));
        Assertions.assertThat(contractorInvoiceCreated.occurredDate().toEpochSecond())
                .isBetween(ZonedDateTime.now().toEpochSecond(), ZonedDateTime.now().toEpochSecond() + 1);
    }
}