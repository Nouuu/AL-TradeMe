package org.larrieulacoste.noe.al.trademe.features.members.application;

import org.larrieulacoste.noe.al.trademe.application.event.NewTradesmanSubscriptionPayment;
import org.larrieulacoste.noe.al.trademe.features.members.application.command.UpdateTradesmanSubscriptionStatus;
import org.larrieulacoste.noe.al.trademe.features.members.domain.SubscriptionStatus;
import org.larrieulacoste.noe.al.trademe.features.members.kernel.MembersCommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

public class TradesmanSubscriptionPaymentFailureListener implements EventSubscriber<NewTradesmanSubscriptionPayment> {

    private final MembersCommandBus commandBus;

    public TradesmanSubscriptionPaymentFailureListener(MembersCommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public void accept(NewTradesmanSubscriptionPayment event) {
        commandBus.send(new UpdateTradesmanSubscriptionStatus(event.getTradesman().entityId, SubscriptionStatus.PAYMENT_FAILED));
    }
}
