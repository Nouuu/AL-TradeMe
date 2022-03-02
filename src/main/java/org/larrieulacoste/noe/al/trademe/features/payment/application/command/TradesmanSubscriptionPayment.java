package org.larrieulacoste.noe.al.trademe.features.payment.application.command;

import org.larrieulacoste.noe.al.trademe.shared_kernel.model.EntityId;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.PaymentMethod;
import org.larrieulacoste.noe.al.trademe.kernel.command.Command;

public record TradesmanSubscriptionPayment(
        EntityId tradesmanId,
        PaymentMethod paymentMethod
) implements Command {
}
