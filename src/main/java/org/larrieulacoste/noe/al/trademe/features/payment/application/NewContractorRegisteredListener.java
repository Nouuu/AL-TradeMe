package org.larrieulacoste.noe.al.trademe.features.payment.application;

import org.larrieulacoste.noe.al.trademe.application.event.NewContractorRegistered;
import org.larrieulacoste.noe.al.trademe.features.payment.application.command.ContractorSubscriptionPayment;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

public class NewContractorRegisteredListener implements EventSubscriber<NewContractorRegistered> {

    private final CommandBus commandBus;

    public NewContractorRegisteredListener(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public void accept(NewContractorRegistered event) {
        ContractorSubscriptionPayment contractor = new ContractorSubscriptionPayment(event.getContractor().entityId, "TODO");
        commandBus.send(contractor);
    }
}
