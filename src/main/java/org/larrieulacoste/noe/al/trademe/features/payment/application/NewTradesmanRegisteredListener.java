package org.larrieulacoste.noe.al.trademe.features.payment.application;

import org.larrieulacoste.noe.al.trademe.application.event.NewTradesmanRegistered;
import org.larrieulacoste.noe.al.trademe.features.payment.application.command.TradesmanSubscriptionPayment;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

public class NewTradesmanRegisteredListener implements EventSubscriber<NewTradesmanRegistered> {

    private final CommandBus commandBus;

    public NewTradesmanRegisteredListener(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public void accept(NewTradesmanRegistered event) {
        TradesmanSubscriptionPayment tradesman = new TradesmanSubscriptionPayment(event.getTradesman().entityId, "TODO");
        commandBus.send(tradesman);
    }
}
