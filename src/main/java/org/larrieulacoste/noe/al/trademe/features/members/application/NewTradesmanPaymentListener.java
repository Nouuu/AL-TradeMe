package org.larrieulacoste.noe.al.trademe.features.members.application;

import org.larrieulacoste.noe.al.trademe.application.event.NewContractorPayment;
import org.larrieulacoste.noe.al.trademe.application.event.NewTradesmanPayment;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

public class NewTradesmanPaymentListener implements EventSubscriber<NewTradesmanPayment> {

    private final CommandBus commandBus;

    public NewTradesmanPaymentListener(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public void accept(NewTradesmanPayment event) {
        System.out.println("New tradesman payment listener triggered ...");
    }
}
