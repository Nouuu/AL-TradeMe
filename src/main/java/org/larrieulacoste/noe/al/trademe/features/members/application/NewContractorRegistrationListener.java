package org.larrieulacoste.noe.al.trademe.features.members.application;

import org.larrieulacoste.noe.al.trademe.application.event.NewContractorRegistration;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

class NewContractorRegistrationListener implements EventSubscriber<NewContractorRegistration> {

    private final ContractorsService contractorsService;

    public NewContractorRegistrationListener(ContractorsService contractorsService) {
        this.contractorsService = contractorsService;
    }

    @Override
    public void accept(NewContractorRegistration event) {
        contractorsService.newContractor(event.getContractorRegistration());
    }
}
