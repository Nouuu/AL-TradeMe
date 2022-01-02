package org.larrieulacoste.noe.al.trademe.features.members.application;

import org.larrieulacoste.noe.al.trademe.application.event.NewContractorSubscriptionPayment;
import org.larrieulacoste.noe.al.trademe.features.members.application.command.UpdateContractorSubscriptionStatus;
import org.larrieulacoste.noe.al.trademe.features.members.domain.SubscriptionStatus;
import org.larrieulacoste.noe.al.trademe.features.members.kernel.MembersCommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

public class NewContractorSubscriptionPaymentListener implements EventSubscriber<NewContractorSubscriptionPayment> {

    private final MembersCommandBus commandBus;

    public NewContractorSubscriptionPaymentListener(MembersCommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public void accept(NewContractorSubscriptionPayment event) {
        commandBus.send(new UpdateContractorSubscriptionStatus(event.getContractor().entityId, SubscriptionStatus.ACTIVE));
    }
}
