package org.larrieulacoste.noe.al.trademe.features.payment.application.command;

import org.larrieulacoste.noe.al.trademe.kernel.command.Command;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.EntityId;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.PaymentMethod;

public record ContractorSubscriptionPayment(
        EntityId contractorId,
        PaymentMethod paymentMethod
) implements Command {
}
