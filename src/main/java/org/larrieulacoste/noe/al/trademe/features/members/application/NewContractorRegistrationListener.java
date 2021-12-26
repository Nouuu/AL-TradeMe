package org.larrieulacoste.noe.al.trademe.features.members.application;

import org.larrieulacoste.noe.al.trademe.application.event.ContractorEventEntity;
import org.larrieulacoste.noe.al.trademe.application.event.NewContractorRegistration;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

public class NewContractorRegistrationListener implements EventSubscriber<NewContractorRegistration> {

    private final CommandBus commandBus;

    public NewContractorRegistrationListener(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public void accept(NewContractorRegistration event) {
        ContractorEventEntity contractor = event.getContractorRegistration();
        commandBus.send(new CreateContractor(contractor.firstname, contractor.lastname, contractor.email, contractor.password));
    }
}