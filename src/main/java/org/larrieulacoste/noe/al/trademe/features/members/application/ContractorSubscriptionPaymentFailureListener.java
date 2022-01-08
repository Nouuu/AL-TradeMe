package org.larrieulacoste.noe.al.trademe.features.members.application;

import org.larrieulacoste.noe.al.trademe.application.event.ContractorNewSubscriptionPayment;
import org.larrieulacoste.noe.al.trademe.features.members.application.command.UpdateContractorSubscriptionStatus;
import org.larrieulacoste.noe.al.trademe.features.members.domain.SubscriptionStatus;
import org.larrieulacoste.noe.al.trademe.features.members.kernel.MembersCommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

public final class ContractorSubscriptionPaymentFailureListener implements EventSubscriber<ContractorNewSubscriptionPayment> {

    private final MembersCommandBus commandBus;

    public ContractorSubscriptionPaymentFailureListener(MembersCommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public void accept(ContractorNewSubscriptionPayment event) {
        commandBus.send(new UpdateContractorSubscriptionStatus(event.contractor.entityId, SubscriptionStatus.PAYMENT_FAILED));
    }
}
