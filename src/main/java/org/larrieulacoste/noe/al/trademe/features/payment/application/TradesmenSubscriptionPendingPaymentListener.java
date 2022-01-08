package org.larrieulacoste.noe.al.trademe.features.payment.application;

import org.larrieulacoste.noe.al.trademe.application.event.TradesmanEventEntity;
import org.larrieulacoste.noe.al.trademe.application.event.TradesmenSubscriptionPendingPayment;
import org.larrieulacoste.noe.al.trademe.features.payment.application.command.TradesmanSubscriptionPayment;
import org.larrieulacoste.noe.al.trademe.features.payment.kernel.PaymentCommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

public final class TradesmenSubscriptionPendingPaymentListener implements EventSubscriber<TradesmenSubscriptionPendingPayment> {

    private final PaymentCommandBus commandBus;

    public TradesmenSubscriptionPendingPaymentListener(PaymentCommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public void accept(TradesmenSubscriptionPendingPayment event) {
        for (TradesmanEventEntity tradesman : event.getTradesmen()) {
            TradesmanSubscriptionPayment tradesmanSubscriptionPayment = new TradesmanSubscriptionPayment(tradesman.entityId, tradesman.paymentMethod);
            commandBus.send(tradesmanSubscriptionPayment);
        }

    }
}
