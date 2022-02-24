package org.larrieulacoste.noe.al.trademe.features.members.application;

import org.larrieulacoste.noe.al.trademe.application.event.ContractorNewRegistration;
import org.larrieulacoste.noe.al.trademe.features.members.application.command.CreateContractor;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

public final class NewContractorRegistrationListener implements EventSubscriber<ContractorNewRegistration> {

    private final CommandBus commandBus;

    public NewContractorRegistrationListener(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public void accept(ContractorNewRegistration event) {
        commandBus.send(new CreateContractor(
                event.firstname(),
                event.lastname(),
                event.email(),
                event.password(),
                event.paymentMethod().paymentMethodType().value,
                event.paymentMethod().paymentInfo()
        ));
    }
}
 