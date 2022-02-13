package org.larrieulacoste.noe.al.trademe.features.payment.application;

import org.larrieulacoste.noe.al.trademe.application.event.ContractorEventEntity;
import org.larrieulacoste.noe.al.trademe.application.event.ContractorsSubscriptionPendingPayment;
import org.larrieulacoste.noe.al.trademe.features.payment.application.command.ContractorSubscriptionPayment;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

public final class ContractorsSubscriptionPendingPaymentListener implements EventSubscriber<ContractorsSubscriptionPendingPayment> {

    private final CommandBus commandBus;

    public ContractorsSubscriptionPendingPaymentListener(CommandBus commandBus) {
        this.commandBus = commandBus;
    }


    @Override
    public void accept(ContractorsSubscriptionPendingPayment event) {
        for (ContractorEventEntity contractor : event.contractors()) {
            ContractorSubscriptionPayment contractorSubscriptionPayment = new ContractorSubscriptionPayment(contractor.entityId(), contractor.paymentMethod());
            commandBus.send(contractorSubscriptionPayment);
        }
    }
}
