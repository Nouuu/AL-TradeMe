package org.larrieulacoste.noe.al.trademe.features.members.application;

import org.larrieulacoste.noe.al.trademe.application.event.ContractorNewSubscriptionPayment;
import org.larrieulacoste.noe.al.trademe.features.members.application.command.UpdateContractorSubscriptionStatus;
import org.larrieulacoste.noe.al.trademe.features.members.domain.SubscriptionStatus;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

public final class ContractorSubscriptionPaymentFailureListener implements EventSubscriber<ContractorNewSubscriptionPayment> {

    private final CommandBus commandBus;

    public ContractorSubscriptionPaymentFailureListener(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public void accept(ContractorNewSubscriptionPayment event) {
        commandBus.send(new UpdateContractorSubscriptionStatus(event.contractorId(), SubscriptionStatus.PAYMENT_FAILED));
    }
}
