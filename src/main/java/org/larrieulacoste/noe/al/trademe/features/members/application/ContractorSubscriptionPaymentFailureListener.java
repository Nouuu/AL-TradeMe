package org.larrieulacoste.noe.al.trademe.features.members.application;

import org.larrieulacoste.noe.al.trademe.application.event.NewContractorSubscriptionPayment;
import org.larrieulacoste.noe.al.trademe.features.members.application.command.UpdateContractorSubscriptionStatus;
import org.larrieulacoste.noe.al.trademe.features.members.domain.SubscriptionStatus;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

public class ContractorSubscriptionPaymentFailureListener implements EventSubscriber<NewContractorSubscriptionPayment> {

    private final CommandBus commandBus;

    public ContractorSubscriptionPaymentFailureListener(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public void accept(NewContractorSubscriptionPayment event) {
        commandBus.send(new UpdateContractorSubscriptionStatus(event.getContractor().entityId, SubscriptionStatus.PAYMENT_FAILED));
    }
}
