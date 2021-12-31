package org.larrieulacoste.noe.al.trademe.features.members.application;

import org.larrieulacoste.noe.al.trademe.application.event.NewContractorPayment;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

public class NewContractorPaymentListener implements EventSubscriber<NewContractorPayment> {

    private final CommandBus commandBus;

    public NewContractorPaymentListener(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public void accept(NewContractorPayment event) {
        System.out.println("New contractor payment listener triggered ..."); // TODO
    }
}
