package org.larrieulacoste.noe.al.trademe.features.payment.application;

import org.larrieulacoste.noe.al.trademe.domain.event.ContractorRegistered;
import org.larrieulacoste.noe.al.trademe.features.payment.application.command.ContractorSubscriptionPayment;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

public final class NewContractorRegisteredListener implements EventSubscriber<ContractorRegistered> {

    private final CommandBus commandBus;

    public NewContractorRegisteredListener(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public void accept(ContractorRegistered event) {
        ContractorSubscriptionPayment contractor = new ContractorSubscriptionPayment(event.contractorId(), event.paymentMethod());
        commandBus.send(contractor);
    }
}
