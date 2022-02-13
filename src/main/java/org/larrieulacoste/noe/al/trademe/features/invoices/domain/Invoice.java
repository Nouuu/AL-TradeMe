package org.larrieulacoste.noe.al.trademe.features.invoices.domain;

import org.larrieulacoste.noe.al.trademe.domain.model.Amount;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.domain.model.MemberType;
import org.larrieulacoste.noe.al.trademe.domain.model.PaymentMethodType;

import java.time.ZonedDateTime;
import java.util.Objects;

public record Invoice(
        EntityId invoiceId,
        MemberType memberType,
        EntityId memberId,
        ZonedDateTime occurredDate,
        PaymentMethodType paymentMethodType,
        Amount amount
) {

    public Invoice {
        Objects.requireNonNull(invoiceId);
        Objects.requireNonNull(memberType);
        Objects.requireNonNull(memberId);
        Objects.requireNonNull(occurredDate);
        Objects.requireNonNull(paymentMethodType);
        Objects.requireNonNull(amount);
    }

    public static Invoice of(EntityId invoiceId, MemberType memberType, EntityId memberId, ZonedDateTime occurredDate, PaymentMethodType paymentMethod, Amount amount) {
        return new Invoice(invoiceId, memberType, memberId, occurredDate, paymentMethod, amount);
    }

}
