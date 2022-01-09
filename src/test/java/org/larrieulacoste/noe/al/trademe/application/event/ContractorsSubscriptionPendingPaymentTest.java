package org.larrieulacoste.noe.al.trademe.application.event;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.List;

class ContractorsSubscriptionPendingPaymentTest {

    EntityId eventEntityId1 = EntityId.of("123");
    EntityId eventEntityId2 = EntityId.of("456");
    EntityId eventEntityId3 = EntityId.of("789");
    ContractorEventEntity contractorEventEntity1 = ContractorEventEntity.withEntityIdOnly(eventEntityId1);
    ContractorEventEntity contractorEventEntity2 = ContractorEventEntity.withEntityIdOnly(eventEntityId2);
    ContractorEventEntity contractorEventEntity3 = ContractorEventEntity.withEntityIdOnly(eventEntityId3);


    @Test
    void withContractors() {
        Assertions.assertThat(ContractorsSubscriptionPendingPayment.withContractors(
                        List.of(
                                contractorEventEntity1,
                                contractorEventEntity2,
                                contractorEventEntity3
                        )
                ))
                .isNotNull()
                .isInstanceOf(ContractorsSubscriptionPendingPayment.class);
    }

    @Test
    void withContractorsNullArgs() {
        Assertions.assertThatThrownBy(() -> ContractorsSubscriptionPendingPayment.withContractors(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void getEventId() {
        ContractorsSubscriptionPendingPayment contractorsSubscriptionPendingPayment = ContractorsSubscriptionPendingPayment.withContractors(
                List.of(
                        contractorEventEntity1,
                        contractorEventEntity2,
                        contractorEventEntity3
                )
        );
        Assertions.assertThat(contractorsSubscriptionPendingPayment.getEventId())
                .isNotNull()
                .isInstanceOf(EventId.class);
    }

    @Test
    void getOccurredDate() {
        ContractorsSubscriptionPendingPayment contractorsSubscriptionPendingPayment = ContractorsSubscriptionPendingPayment.withContractors(
                List.of(
                        contractorEventEntity1,
                        contractorEventEntity2,
                        contractorEventEntity3
                )
        );
        Assertions.assertThat(contractorsSubscriptionPendingPayment.getOccurredDate().toEpochSecond())
                .isBetween(ZonedDateTime.now().toEpochSecond(), ZonedDateTime.now().toEpochSecond() + 1);
    }

    @Test
    void getContractors() {
        ContractorsSubscriptionPendingPayment contractorsSubscriptionPendingPayment = ContractorsSubscriptionPendingPayment.withContractors(
                List.of(
                        contractorEventEntity1,
                        contractorEventEntity2,
                        contractorEventEntity3
                )
        );
        Assertions.assertThat(contractorsSubscriptionPendingPayment.getContractors())
                .containsExactlyElementsOf(List.of(contractorEventEntity1,
                        contractorEventEntity2,
                        contractorEventEntity3));
    }
}