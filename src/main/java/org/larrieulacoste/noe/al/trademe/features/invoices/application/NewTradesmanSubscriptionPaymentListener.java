package org.larrieulacoste.noe.al.trademe.features.invoices.application;

import org.larrieulacoste.noe.al.trademe.application.event.NewTradesmanSubscriptionPayment;
import org.larrieulacoste.noe.al.trademe.domain.model.MemberType;
import org.larrieulacoste.noe.al.trademe.features.invoices.application.command.CreateInvoice;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

public class NewTradesmanSubscriptionPaymentListener implements EventSubscriber<NewTradesmanSubscriptionPayment> {

    private final CommandBus commandBus;

    public NewTradesmanSubscriptionPaymentListener(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public void accept(NewTradesmanSubscriptionPayment event) {
        commandBus.send(new CreateInvoice(MemberType.TRADESMAN, event.getTradesman().entityId, event.getAmount()));
    }
}
