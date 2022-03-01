package org.larrieulacoste.noe.al.trademe.features.invoices.application;

import org.larrieulacoste.noe.al.trademe.domain.event.TradesmanNewSubscriptionPayment;
import org.larrieulacoste.noe.al.trademe.features.invoices.application.command.CreateInvoice;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.MemberType;

public final class NewTradesmanSubscriptionPaymentListener implements EventSubscriber<TradesmanNewSubscriptionPayment> {

    private final CommandBus commandBus;

    public NewTradesmanSubscriptionPaymentListener(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public void accept(TradesmanNewSubscriptionPayment event) {
        commandBus.send(new CreateInvoice(
                MemberType.TRADESMAN,
                event.tradesmanId(),
                event.paymentMethod().paymentMethodType(),
                event.amount()
        ));
    }
}
