package org.larrieulacoste.noe.al.trademe.application.event;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;

class ContractorSubscriptionPaymentFailureTest {
    EntityId eventEntityId = EntityId.of("123");
    ContractorEventEntity contractorEventEntity = ContractorEventEntity.withEntityIdOnly(eventEntityId);

    @Test
    void withContractor() {
        Assertions.assertThat(ContractorSubscriptionPaymentFailure.withContractor(contractorEventEntity))
                .isNotNull()
                .isInstanceOf(ContractorSubscriptionPaymentFailure.class);
    }

    @Test
    void withNullContractor() {
        Assertions.assertThatThrownBy(() -> ContractorSubscriptionPaymentFailure.withContractor(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void getEventId() {
        ContractorSubscriptionPaymentFailure contractorSubscriptionPaymentFailure =
                ContractorSubscriptionPaymentFailure.withContractor(contractorEventEntity);
        Assertions.assertThat(contractorSubscriptionPaymentFailure.getEventId())
                .isNotNull()
                .isInstanceOf(EventId.class);
    }

    @Test
    void getOccurredDate() {
        ContractorSubscriptionPaymentFailure contractorSubscriptionPaymentFailure =
                ContractorSubscriptionPaymentFailure.withContractor(contractorEventEntity);
        Assertions.assertThat(contractorSubscriptionPaymentFailure.getOccurredDate().toEpochSecond())
                .isBetween(ZonedDateTime.now().toEpochSecond(), ZonedDateTime.now().toEpochSecond() + 1);
    }
}