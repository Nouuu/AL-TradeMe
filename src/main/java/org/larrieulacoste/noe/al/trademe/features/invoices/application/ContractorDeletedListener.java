package org.larrieulacoste.noe.al.trademe.features.invoices.application;

import org.larrieulacoste.noe.al.trademe.application.event.ContractorDeleted;
import org.larrieulacoste.noe.al.trademe.application.event.NewContractorSubscriptionPayment;
import org.larrieulacoste.noe.al.trademe.domain.model.MemberType;
import org.larrieulacoste.noe.al.trademe.features.invoices.application.command.CreateInvoice;
import org.larrieulacoste.noe.al.trademe.features.invoices.application.command.DeleteContractorInvoices;
import org.larrieulacoste.noe.al.trademe.features.invoices.kernel.InvoicesCommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

public class ContractorDeletedListener implements EventSubscriber<ContractorDeleted> {

    private final InvoicesCommandBus commandBus;

    public ContractorDeletedListener(InvoicesCommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public void accept(ContractorDeleted event) {
        commandBus.send(new DeleteContractorInvoices(event.getContractor().entityId));
    }
}
