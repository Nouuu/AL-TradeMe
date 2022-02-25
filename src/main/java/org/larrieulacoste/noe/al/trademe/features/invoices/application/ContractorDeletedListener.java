package org.larrieulacoste.noe.al.trademe.features.invoices.application;

import org.larrieulacoste.noe.al.trademe.application.event.ContractorDeleted;
import org.larrieulacoste.noe.al.trademe.features.invoices.application.command.DeleteContractorInvoices;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

public final class ContractorDeletedListener implements EventSubscriber<ContractorDeleted> {

    private final CommandBus commandBus;

    public ContractorDeletedListener(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public void accept(ContractorDeleted event) {
        commandBus.send(new DeleteContractorInvoices(event.contractorId()));
    }
}
