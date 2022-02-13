package org.larrieulacoste.noe.al.trademe.application.event;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;

class ContractorUpdatedTest {
    EntityId eventEntityId = EntityId.of("123");


    @Test
    void withContractor() {
        Assertions.assertThat(ContractorUpdated.withContractor(ContractorEventEntity.withEntityIdOnly(eventEntityId)))
                .isNotNull()
                .isInstanceOf(ContractorUpdated.class);

    }

    @Test
    void withContractorNullArgs() {
        Assertions.assertThatThrownBy(() -> ContractorUpdated.withContractor(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void getEventId() {
        ContractorUpdated contractorUpdated = ContractorUpdated.withContractor(ContractorEventEntity.withEntityIdOnly(eventEntityId));
        Assertions.assertThat(contractorUpdated.eventId())
                .isNotNull()
                .isInstanceOf(EventId.class);
    }

    @Test
    void getOccurredDate() {
        ContractorUpdated contractorUpdated = ContractorUpdated.withContractor(ContractorEventEntity.withEntityIdOnly(eventEntityId));
        Assertions.assertThat(contractorUpdated.occurredDate().toEpochSecond())
                .isBetween(ZonedDateTime.now().toEpochSecond(), ZonedDateTime.now().toEpochSecond() + 1);
    }

}