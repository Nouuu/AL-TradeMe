package org.larrieulacoste.noe.al.trademe.features.members.application;

import org.larrieulacoste.noe.al.trademe.application.event.ContractorNewSubscriptionPayment;
import org.larrieulacoste.noe.al.trademe.features.members.application.command.PublishContractorsPendingSubscriptionPayment;
import org.larrieulacoste.noe.al.trademe.features.members.application.command.PublishTradesmenPendingSubscriptionPayment;
import org.larrieulacoste.noe.al.trademe.features.members.kernel.MembersCommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

public class MonthlySubscriptionPaymentListener implements EventSubscriber<ContractorNewSubscriptionPayment> {

    private final MembersCommandBus commandBus;

    public MonthlySubscriptionPaymentListener(MembersCommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public void accept(ContractorNewSubscriptionPayment event) {
        commandBus.send(new PublishContractorsPendingSubscriptionPayment());
        commandBus.send(new PublishTradesmenPendingSubscriptionPayment());
    }
}
