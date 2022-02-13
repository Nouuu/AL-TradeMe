package org.larrieulacoste.noe.al.trademe.application.event;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.domain.model.PaymentMethod;

class ContractorEventEntityTest {
    PaymentMethod paymentMethod = PaymentMethod.withPaypal("paypalemail");

    @Test
    void of() {
        EntityId entityId = EntityId.of("123");
        ContractorEventEntity contractorEventEntity =
                ContractorEventEntity.of(entityId, "firstname", "lastname", "email", "password", paymentMethod);
        Assertions.assertThat(contractorEventEntity.entityId()).isEqualTo(entityId);
        Assertions.assertThat(contractorEventEntity.firstname()).isEqualTo("firstname");
        Assertions.assertThat(contractorEventEntity.lastname()).isEqualTo("lastname");
        Assertions.assertThat(contractorEventEntity.email()).isEqualTo("email");
        Assertions.assertThat(contractorEventEntity.password()).isEqualTo("password");
        Assertions.assertThat(contractorEventEntity.paymentMethod()).isEqualTo(paymentMethod);
    }

    @Test
    void withoutPassword() {
        EntityId entityId = EntityId.of("123");
        ContractorEventEntity contractorEventEntity =
                ContractorEventEntity.withoutPassword(entityId, "firstname", "lastname", "email", paymentMethod);
        Assertions.assertThat(contractorEventEntity.entityId()).isEqualTo(entityId);
        Assertions.assertThat(contractorEventEntity.firstname()).isEqualTo("firstname");
        Assertions.assertThat(contractorEventEntity.lastname()).isEqualTo("lastname");
        Assertions.assertThat(contractorEventEntity.email()).isEqualTo("email");
        Assertions.assertThat(contractorEventEntity.paymentMethod()).isEqualTo(paymentMethod);
        Assertions.assertThat(contractorEventEntity.password()).isNull();
    }

    @Test
    void withEntityIdOnly() {
        EntityId entityId = EntityId.of("123");
        ContractorEventEntity contractorEventEntity =
                ContractorEventEntity.withEntityIdOnly(entityId);
        Assertions.assertThat(contractorEventEntity.entityId()).isEqualTo(entityId);
        Assertions.assertThat(contractorEventEntity.firstname()).isNull();
        Assertions.assertThat(contractorEventEntity.lastname()).isNull();
        Assertions.assertThat(contractorEventEntity.email()).isNull();
        Assertions.assertThat(contractorEventEntity.password()).isNull();
        Assertions.assertThat(contractorEventEntity.paymentMethod()).isNull();
    }

    @Test
    void withEntityIdAndPaymentMethodOnly() {
        EntityId entityId = EntityId.of("123");
        ContractorEventEntity contractorEventEntity =
                ContractorEventEntity.withEntityIdAndPaymentMethodOnly(entityId, paymentMethod);
        Assertions.assertThat(contractorEventEntity.entityId()).isEqualTo(entityId);
        Assertions.assertThat(contractorEventEntity.firstname()).isNull();
        Assertions.assertThat(contractorEventEntity.lastname()).isNull();
        Assertions.assertThat(contractorEventEntity.email()).isNull();
        Assertions.assertThat(contractorEventEntity.password()).isNull();
        Assertions.assertThat(contractorEventEntity.paymentMethod()).isEqualTo(paymentMethod);

    }
}