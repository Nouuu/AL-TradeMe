package org.larrieulacoste.noe.al.trademe.features.payment.application.command;

import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.domain.model.PaymentMethod;
import org.larrieulacoste.noe.al.trademe.kernel.command.Command;

public record ContractorSubscriptionPayment(
        EntityId contractorId,
        PaymentMethod paymentMethod
) implements Command {
}
