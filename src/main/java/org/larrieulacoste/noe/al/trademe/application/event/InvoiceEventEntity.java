package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.domain.model.Amount;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.domain.model.MemberType;
import org.larrieulacoste.noe.al.trademe.domain.model.PaymentMethodType;

import java.time.ZonedDateTime;

public record InvoiceEventEntity(
        EntityId invoiceId,
        MemberType memberType,
        EntityId memberId,
        ZonedDateTime occurredDate,
        PaymentMethodType paymentMethodType,
        Amount amount
) {

    public static InvoiceEventEntity of(EntityId invoiceId, MemberType memberType, EntityId memberId, ZonedDateTime occurredDate, PaymentMethodType paymentMethodType, Amount amount) {
        return new InvoiceEventEntity(invoiceId, memberType, memberId, occurredDate, paymentMethodType, amount);
    }
}
