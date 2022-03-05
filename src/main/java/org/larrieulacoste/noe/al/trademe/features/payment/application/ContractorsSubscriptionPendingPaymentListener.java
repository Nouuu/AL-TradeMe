package org.larrieulacoste.noe.al.trademe.features.payment.application;

import org.larrieulacoste.noe.al.trademe.domain.event.ContractorsSubscriptionPendingPayment;
import org.larrieulacoste.noe.al.trademe.features.payment.application.command.ContractorSubscriptionPayment;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.MemberPayment;

public final class ContractorsSubscriptionPendingPaymentListener implements EventSubscriber<ContractorsSubscriptionPendingPayment> {

    private final CommandBus commandBus;

    public ContractorsSubscriptionPendingPaymentListener(CommandBus commandBus) {
        this.commandBus = commandBus;
    }


    @Override
    public void accept(ContractorsSubscriptionPendingPayment event) {
        for (MemberPayment contractor : event.contractorsPayments()) {
            ContractorSubscriptionPayment contractorSubscriptionPayment = new ContractorSubscriptionPayment(contractor.memberId(), contractor.paymentMethod());
            commandBus.send(contractorSubscriptionPayment);
        }
    }
}
