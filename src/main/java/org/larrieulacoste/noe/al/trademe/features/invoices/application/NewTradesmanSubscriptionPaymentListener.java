package org.larrieulacoste.noe.al.trademe.features.invoices.application;

import org.larrieulacoste.noe.al.trademe.application.event.TradesmanNewSubscriptionPayment;
import org.larrieulacoste.noe.al.trademe.domain.model.MemberType;
import org.larrieulacoste.noe.al.trademe.features.invoices.application.command.CreateInvoice;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

public final class NewTradesmanSubscriptionPaymentListener implements EventSubscriber<TradesmanNewSubscriptionPayment> {

    private final CommandBus commandBus;

    public NewTradesmanSubscriptionPaymentListener(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public void accept(TradesmanNewSubscriptionPayment event) {
        commandBus.send(new CreateInvoice(MemberType.TRADESMAN, event.tradesman().entityId(), event.paymentMethod().paymentMethodType(), event.amount()));
    }
}
