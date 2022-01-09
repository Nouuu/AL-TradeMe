package org.larrieulacoste.noe.al.trademe.features.payment.application;

import org.larrieulacoste.noe.al.trademe.application.event.ContractorEventEntity;
import org.larrieulacoste.noe.al.trademe.application.event.ContractorsSubscriptionPendingPayment;
import org.larrieulacoste.noe.al.trademe.features.payment.application.command.ContractorSubscriptionPayment;
import org.larrieulacoste.noe.al.trademe.features.payment.kernel.PaymentCommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

public final class ContractorsSubscriptionPendingPaymentListener implements EventSubscriber<ContractorsSubscriptionPendingPayment> {

    private final PaymentCommandBus commandBus;

    public ContractorsSubscriptionPendingPaymentListener(PaymentCommandBus commandBus) {
        this.commandBus = commandBus;
    }


    @Override
    public void accept(ContractorsSubscriptionPendingPayment event) {
        for (ContractorEventEntity contractor : event.getContractors()) {
            ContractorSubscriptionPayment contractorSubscriptionPayment = new ContractorSubscriptionPayment(contractor.entityId, contractor.paymentMethod);
            commandBus.send(contractorSubscriptionPayment);
        }
    }
}
