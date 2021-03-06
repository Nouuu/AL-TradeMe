package org.larrieulacoste.noe.al.trademe.features.payment.application;

import org.larrieulacoste.noe.al.trademe.domain.event.TradesmenSubscriptionPendingPayment;
import org.larrieulacoste.noe.al.trademe.features.payment.application.command.TradesmanSubscriptionPayment;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.MemberPayment;

public final class TradesmenSubscriptionPendingPaymentListener implements EventSubscriber<TradesmenSubscriptionPendingPayment> {

    private final CommandBus commandBus;

    public TradesmenSubscriptionPendingPaymentListener(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public void accept(TradesmenSubscriptionPendingPayment event) {
        for (MemberPayment tradesmanPayment : event.tradesmen()) {
            TradesmanSubscriptionPayment tradesmanSubscriptionPayment = new TradesmanSubscriptionPayment(tradesmanPayment.memberId(), tradesmanPayment.paymentMethod());
            commandBus.send(tradesmanSubscriptionPayment);
        }
    }
}
