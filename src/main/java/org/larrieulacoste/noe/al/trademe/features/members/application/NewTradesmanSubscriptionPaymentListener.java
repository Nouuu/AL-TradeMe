package org.larrieulacoste.noe.al.trademe.features.members.application;

import org.larrieulacoste.noe.al.trademe.application.event.TradesmanNewSubscriptionPayment;
import org.larrieulacoste.noe.al.trademe.features.members.application.command.UpdateTradesmanSubscriptionStatus;
import org.larrieulacoste.noe.al.trademe.features.members.domain.SubscriptionStatus;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

public final class NewTradesmanSubscriptionPaymentListener implements EventSubscriber<TradesmanNewSubscriptionPayment> {

    private final CommandBus commandBus;

    public NewTradesmanSubscriptionPaymentListener(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public void accept(TradesmanNewSubscriptionPayment event) {
        commandBus.send(new UpdateTradesmanSubscriptionStatus(event.tradesman.entityId, SubscriptionStatus.ACTIVE));
    }
}
