package org.larrieulacoste.noe.al.trademe.features.invoices.application;

import org.larrieulacoste.noe.al.trademe.application.event.NewContractorSubscriptionPayment;
import org.larrieulacoste.noe.al.trademe.domain.model.MemberType;
import org.larrieulacoste.noe.al.trademe.features.invoices.application.command.CreateInvoice;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

public class NewContractorSubscriptionPaymentListener implements EventSubscriber<NewContractorSubscriptionPayment> {

    private final CommandBus commandBus;

    public NewContractorSubscriptionPaymentListener(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public void accept(NewContractorSubscriptionPayment event) {
        commandBus.send(new CreateInvoice(MemberType.CONTRACTOR,event.getContractor().entityId, 0)); // TODO
    }
}
