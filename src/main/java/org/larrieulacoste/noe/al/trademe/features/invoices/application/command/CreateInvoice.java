package org.larrieulacoste.noe.al.trademe.features.invoices.application.command;

import org.larrieulacoste.noe.al.trademe.kernel.command.Command;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.Amount;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.EntityId;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.MemberType;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.PaymentMethodType;

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
