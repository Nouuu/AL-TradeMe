package org.larrieulacoste.noe.al.trademe.features.payment.application;

import org.larrieulacoste.noe.al.trademe.application.event.TradesmanRegistered;
import org.larrieulacoste.noe.al.trademe.features.payment.application.command.TradesmanSubscriptionPayment;
import org.larrieulacoste.noe.al.trademe.features.payment.kernel.PaymentCommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

public class NewTradesmanRegisteredListener implements EventSubscriber<TradesmanRegistered> {

    private final PaymentCommandBus commandBus;

    public NewTradesmanRegisteredListener(PaymentCommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public void accept(TradesmanRegistered event) {
        TradesmanSubscriptionPayment tradesman = new TradesmanSubscriptionPayment(event.tradesman.entityId, "TODO");
        commandBus.send(tradesman);
    }
}
