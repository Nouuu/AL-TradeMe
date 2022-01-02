package org.larrieulacoste.noe.al.trademe.features.members.application;

import org.larrieulacoste.noe.al.trademe.application.event.ContractorEventEntity;
import org.larrieulacoste.noe.al.trademe.application.event.NewContractorRegistration;
import org.larrieulacoste.noe.al.trademe.features.members.application.command.CreateContractor;
import org.larrieulacoste.noe.al.trademe.features.members.kernel.MembersCommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

public class NewContractorRegistrationListener implements EventSubscriber<NewContractorRegistration> {

    private final MembersCommandBus commandBus;

    public NewContractorRegistrationListener(MembersCommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public void accept(NewContractorRegistration event) {
        ContractorEventEntity contractor = event.getContractorRegistration();
        commandBus.send(new CreateContractor(contractor.firstname, contractor.lastname, contractor.email, contractor.password));
    }
}
