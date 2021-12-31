package org.larrieulacoste.noe.al.trademe.features.members.application;

import org.larrieulacoste.noe.al.trademe.application.event.NewTradesmanSubscriptionPayment;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

public class NewTradesmanPaymentListener implements EventSubscriber<NewTradesmanSubscriptionPayment> {

    private final CommandBus commandBus;

    public NewTradesmanPaymentListener(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public void accept(NewTradesmanSubscriptionPayment event) {
        System.out.println("New tradesman payment listener triggered ..."); // TODO
    }
}
