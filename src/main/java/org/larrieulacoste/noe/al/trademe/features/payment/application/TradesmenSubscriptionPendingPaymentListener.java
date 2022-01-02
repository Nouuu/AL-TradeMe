package org.larrieulacoste.noe.al.trademe.features.payment.application;

import org.larrieulacoste.noe.al.trademe.application.event.TradesmanEventEntity;
import org.larrieulacoste.noe.al.trademe.application.event.TradesmenSubscriptionPendingPayment;
import org.larrieulacoste.noe.al.trademe.features.payment.application.command.TradesmanSubscriptionPayment;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

public class TradesmenSubscriptionPendingPaymentListener implements EventSubscriber<TradesmenSubscriptionPendingPayment> {

    private final CommandBus commandBus;

    public TradesmenSubscriptionPendingPaymentListener(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public void accept(TradesmenSubscriptionPendingPayment event) {
        for (TradesmanEventEntity tradesman : event.getTradesmen()) {
            TradesmanSubscriptionPayment tradesmanSubscriptionPayment = new TradesmanSubscriptionPayment(tradesman.entityId, "TODO");
            commandBus.send(tradesmanSubscriptionPayment);
        }

    }
}
