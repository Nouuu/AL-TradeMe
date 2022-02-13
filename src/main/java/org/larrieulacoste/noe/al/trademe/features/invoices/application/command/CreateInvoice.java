package org.larrieulacoste.noe.al.trademe.features.invoices.application.command;

import org.larrieulacoste.noe.al.trademe.domain.model.Amount;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.domain.model.MemberType;
import org.larrieulacoste.noe.al.trademe.domain.model.PaymentMethodType;
import org.larrieulacoste.noe.al.trademe.kernel.command.Command;

import java.util.Objects;

public record CreateInvoice(
        MemberType memberType,
        EntityId memberId,
        PaymentMethodType paymentMethodType,
        Amount amount
) implements Command {

    public CreateInvoice {
        Objects.requireNonNull(memberType);
        Objects.requireNonNull(memberId);
        Objects.requireNonNull(paymentMethodType);
        Objects.requireNonNull(amount);
    }
}
