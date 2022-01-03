package org.larrieulacoste.noe.al.trademe.features.invoices.application;

import org.larrieulacoste.noe.al.trademe.application.event.ContractorNewSubscriptionPayment;
import org.larrieulacoste.noe.al.trademe.domain.model.MemberType;
import org.larrieulacoste.noe.al.trademe.features.invoices.application.command.CreateInvoice;
import org.larrieulacoste.noe.al.trademe.features.invoices.kernel.InvoicesCommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

public class NewContractorSubscriptionPaymentListener implements EventSubscriber<ContractorNewSubscriptionPayment> {

    private final InvoicesCommandBus commandBus;

    public NewContractorSubscriptionPaymentListener(InvoicesCommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public void accept(ContractorNewSubscriptionPayment event) {
        commandBus.send(new CreateInvoice(MemberType.CONTRACTOR, event.getContractor().entityId, event.getAmount()));
    }
}
