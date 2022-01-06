package org.larrieulacoste.noe.al.trademe.application.event;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;

class ContractorRegisteredTest {
    EntityId eventEntityId = EntityId.of("123");

    @Test
    void withContractor() {
        Assertions.assertThat(ContractorRegistered.withContractor(ContractorEventEntity.withEntityIdOnly(eventEntityId)))
                .isNotNull()
                .isInstanceOf(ContractorRegistered.class);

    }

    @Test
    void withContractorNullArgs() {
        Assertions.assertThatThrownBy(() -> ContractorRegistered.withContractor(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void getEventId() {
        ContractorRegistered contractorRegistered = ContractorRegistered.withContractor(ContractorEventEntity.withEntityIdOnly(eventEntityId));
        Assertions.assertThat(contractorRegistered.getEventId())
                .isNotNull()
                .isInstanceOf(EventId.class);
    }

    @Test
    void getOccurredDate() {
        ContractorRegistered contractorRegistered = ContractorRegistered.withContractor(ContractorEventEntity.withEntityIdOnly(eventEntityId));
        Assertions.assertThat(contractorRegistered.getOccurredDate().toEpochSecond())
                .isBetween(ZonedDateTime.now().toEpochSecond(), ZonedDateTime.now().toEpochSecond() + 1);
    }

}