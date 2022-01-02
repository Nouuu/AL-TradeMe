package org.larrieulacoste.noe.al.trademe.features.payment.application;

import org.larrieulacoste.noe.al.trademe.application.event.NewTradesmanRegistered;
import org.larrieulacoste.noe.al.trademe.features.payment.application.command.TradesmanSubscriptionPayment;
import org.larrieulacoste.noe.al.trademe.features.payment.kernel.PaymentCommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

public class NewTradesmanRegisteredListener implements EventSubscriber<NewTradesmanRegistered> {

    private final PaymentCommandBus commandBus;

    public NewTradesmanRegisteredListener(PaymentCommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public void accept(NewTradesmanRegistered event) {
        TradesmanSubscriptionPayment tradesman = new TradesmanSubscriptionPayment(event.getTradesman().entityId, "TODO");
        commandBus.send(tradesman);
    }
}
