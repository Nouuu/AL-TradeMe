package org.larrieulacoste.noe.al.trademe.application.event;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.larrieulacoste.noe.al.trademe.domain.model.*;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceEventEntityTest {

    @Test
    void of() {
        EntityId invoiceId = EntityId.of("123");
        MemberType memberType = MemberType.CONTRACTOR;
        EntityId memberId = EntityId.of("456");
        ZonedDateTime occurredDate = ZonedDateTime.now();
        PaymentMethodType paymentMethodType = PaymentMethodType.PAYPAL;
        Amount amount = Amount.of(12);
        InvoiceEventEntity invoiceEventEntity = InvoiceEventEntity.of(invoiceId, memberType, memberId, occurredDate, paymentMethodType, amount);

        Assertions.assertThat(invoiceEventEntity.invoiceId).isEqualTo(invoiceId);
        Assertions.assertThat(invoiceEventEntity.memberType).isEqualTo(memberType);
        Assertions.assertThat(invoiceEventEntity.memberId).isEqualTo(memberId);
        Assertions.assertThat(invoiceEventEntity.occurredDate).isEqualTo(occurredDate);
        Assertions.assertThat(invoiceEventEntity.paymentMethodType.value).isEqualTo(paymentMethodType.value);
        Assertions.assertThat(invoiceEventEntity.amount).isEqualTo(amount);
    }
}