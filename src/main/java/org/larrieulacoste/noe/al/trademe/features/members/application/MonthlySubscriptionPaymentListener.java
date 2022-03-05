package org.larrieulacoste.noe.al.trademe.features.members.application;

import org.larrieulacoste.noe.al.trademe.domain.event.ContractorNewSubscriptionPayment;
import org.larrieulacoste.noe.al.trademe.features.members.application.command.PublishContractorsPendingSubscriptionPayment;
import org.larrieulacoste.noe.al.trademe.features.members.application.command.PublishTradesmenPendingSubscriptionPayment;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

public final class MonthlySubscriptionPaymentListener implements EventSubscriber<ContractorNewSubscriptionPayment> {

    private final CommandBus commandBus;

    public MonthlySubscriptionPaymentListener(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public void accept(ContractorNewSubscriptionPayment event) {
        commandBus.send(new PublishContractorsPendingSubscriptionPayment());
        commandBus.send(new PublishTradesmenPendingSubscriptionPayment());
    }
}
