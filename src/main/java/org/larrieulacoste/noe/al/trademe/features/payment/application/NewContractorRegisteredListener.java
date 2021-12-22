package org.larrieulacoste.noe.al.trademe.features.payment.application;

import org.larrieulacoste.noe.al.trademe.application.event.NewContractorRegistered;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

public class NewContractorRegistrationListener implements EventSubscriber<NewContractorRegistered> {

    private final CommandBus commandBus;

    public NewContractorRegistrationListener(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public void accept(NewContractorRegistered event) {
        ContractorPayment contractor = new ContractorPayment(event.getContractor().entityId, "TODO");
        commandBus.send(contractor);
    }
}
