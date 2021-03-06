package org.larrieulacoste.noe.al.trademe.features.payment.application;

import org.larrieulacoste.noe.al.trademe.domain.event.TradesmanRegistered;
import org.larrieulacoste.noe.al.trademe.features.payment.application.command.TradesmanSubscriptionPayment;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

public final class NewTradesmanRegisteredListener implements EventSubscriber<TradesmanRegistered> {

    private final CommandBus commandBus;

    public NewTradesmanRegisteredListener(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public void accept(TradesmanRegistered event) {
        TradesmanSubscriptionPayment tradesman = new TradesmanSubscriptionPayment(event.tradesmanId(), event.paymentMethod());
        commandBus.send(tradesman);
    }
}
