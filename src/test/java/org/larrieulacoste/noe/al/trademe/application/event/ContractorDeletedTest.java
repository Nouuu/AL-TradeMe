package org.larrieulacoste.noe.al.trademe.application.event;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;

class ContractorDeletedTest {
    EntityId eventEntityId = EntityId.of("123");

    @Test
    void withContractor() {
        Assertions.assertThat(ContractorDeleted.withContractor(ContractorEventEntity.withEntityIdOnly(eventEntityId)))
                .isNotNull()
                .isInstanceOf(ContractorDeleted.class);
    }

    @Test
    void withContractorNullArgs() {
        Assertions.assertThatThrownBy(() -> ContractorDeleted.withContractor(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void getEventId() {
        ContractorDeleted contractorDeleted = ContractorDeleted.withContractor(ContractorEventEntity.withEntityIdOnly(eventEntityId));
        Assertions.assertThat(contractorDeleted.getEventId())
                .isNotNull()
                .isInstanceOf(EventId.class);
    }

    @Test
    void getOccurredDate() {
        ContractorDeleted contractorDeleted = ContractorDeleted.withContractor(ContractorEventEntity.withEntityIdOnly(eventEntityId));
        Assertions.assertThat(contractorDeleted.getOccurredDate().toEpochSecond())
                .isBetween(ZonedDateTime.now().toEpochSecond(), ZonedDateTime.now().toEpochSecond() + 1);
    }
}