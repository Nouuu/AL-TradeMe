package org.larrieulacoste.noe.al.trademe.domain.event;

import org.larrieulacoste.noe.al.trademe.shared_kernel.model.Amount;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.EntityId;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.MemberType;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.PaymentMethodType;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.Objects;

public record ContractorInvoiceCreated(
        EventId eventId,
        ZonedDateTime occurredDate,
        EntityId invoiceId,
        MemberType memberType,
        EntityId memberId,
        ZonedDateTime invoiceOccurredDate,
        PaymentMethodType paymentMethodType,
        Amount amount
) implements ApplicationEvent {

    public ContractorInvoiceCreated {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(occurredDate);
        Objects.requireNonNull(invoiceId);
        Objects.requireNonNull(memberType);
        Objects.requireNonNull(memberId);
        Objects.requireNonNull(invoiceOccurredDate);
        Objects.requireNonNull(paymentMethodType);
        Objects.requireNonNull(amount);
    }

    public static ContractorInvoiceCreated of(
            EntityId invoiceId,
            MemberType memberType,
            EntityId memberId,
            ZonedDateTime invoiceOccurredDate,
            PaymentMethodType paymentMethodType,
            Amount amount
    ) {
        return new ContractorInvoiceCreated(
                EventId.create(),
                ZonedDateTime.now(),
                invoiceId,
                memberType,
                memberId,
                invoiceOccurredDate,
                paymentMethodType,
                amount
        );
    }
}
