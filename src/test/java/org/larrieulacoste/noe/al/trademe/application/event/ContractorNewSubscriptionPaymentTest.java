package org.larrieulacoste.noe.al.trademe.application.event;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.larrieulacoste.noe.al.trademe.domain.model.Amount;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.domain.model.PaymentMethod;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;

class ContractorNewSubscriptionPaymentTest {

    EntityId eventEntityId = EntityId.of("123");
    PaymentMethod paymentMethod = PaymentMethod.withPaypal("paypalmail");

    @Test
    void withContractorAndAmount() {
        Assertions.assertThat(ContractorNewSubscriptionPayment.of(ContractorEventEntity.withEntityIdOnly(eventEntityId), paymentMethod, Amount.of(0)))
                .isNotNull()
                .isInstanceOf(ContractorNewSubscriptionPayment.class);
    }

    @Test
    void withContractorAndAmountNullArgs() {
        ContractorEventEntity contractorEventEntity = ContractorEventEntity.withEntityIdOnly(eventEntityId);
        Amount amount = Amount.of(0);
        Assertions.assertThatThrownBy(() -> ContractorNewSubscriptionPayment.of(null, paymentMethod, amount))
                .isInstanceOf(NullPointerException.class);
        Assertions.assertThatThrownBy(() -> ContractorNewSubscriptionPayment.of(contractorEventEntity, null, amount))
                .isInstanceOf(NullPointerException.class);
        Assertions.assertThatThrownBy(() -> ContractorNewSubscriptionPayment.of(contractorEventEntity, paymentMethod, null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void getEventId() {
        ContractorNewSubscriptionPayment contractorNewSubscriptionPayment =
                ContractorNewSubscriptionPayment.of(ContractorEventEntity.withEntityIdOnly(eventEntityId), paymentMethod, Amount.of(0));
        Assertions.assertThat(contractorNewSubscriptionPayment.getEventId())
                .isNotNull()
                .isInstanceOf(EventId.class);
    }

    @Test
    void getOccurredDate() {
        ContractorNewSubscriptionPayment contractorNewSubscriptionPayment =
                ContractorNewSubscriptionPayment.of(ContractorEventEntity.withEntityIdOnly(eventEntityId), paymentMethod, Amount.of(0));
        Assertions.assertThat(contractorNewSubscriptionPayment.getOccurredDate().toEpochSecond())
                .isBetween(ZonedDateTime.now().toEpochSecond(), ZonedDateTime.now().toEpochSecond() + 1);

    }
}