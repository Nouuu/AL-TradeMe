package org.larrieulacoste.noe.al.trademe.features.payment.application;

import org.larrieulacoste.noe.al.trademe.application.event.ContractorRegistered;
import org.larrieulacoste.noe.al.trademe.features.payment.application.command.ContractorSubscriptionPayment;
import org.larrieulacoste.noe.al.trademe.features.payment.kernel.PaymentCommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

public class NewContractorRegisteredListener implements EventSubscriber<ContractorRegistered> {

    private final PaymentCommandBus commandBus;

    public NewContractorRegisteredListener(PaymentCommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public void accept(ContractorRegistered event) {
        ContractorSubscriptionPayment contractor = new ContractorSubscriptionPayment(event.contractor.entityId, "TODO");
        commandBus.send(contractor);
    }
}
