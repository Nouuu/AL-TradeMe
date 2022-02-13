package org.larrieulacoste.noe.al.trademe.features.members.application;

import org.larrieulacoste.noe.al.trademe.application.event.ContractorEventEntity;
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
        ContractorEventEntity contractor = event.contractor;
        commandBus.send(new CreateContractor(contractor.firstname, contractor.lastname, contractor.email,
                contractor.password, contractor.paymentMethod.paymentMethodType.value, contractor.paymentMethod.paymentInfo));
    }
}
 