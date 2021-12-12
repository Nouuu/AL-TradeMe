package org.larrieulacoste.noe.al.trademe.features.members.service;

import org.larrieulacoste.noe.al.trademe.application.event.NewContractorRegistration;
import org.larrieulacoste.noe.al.trademe.domain.Repository;
import org.larrieulacoste.noe.al.trademe.domain.entity.Contractor;
import org.larrieulacoste.noe.al.trademe.domain.entity.User;
import org.larrieulacoste.noe.al.trademe.domain.model.ContractorRegistration;
import org.larrieulacoste.noe.al.trademe.domain.model.EmailAddress;
import org.larrieulacoste.noe.al.trademe.domain.model.NotEmptyString;
import org.larrieulacoste.noe.al.trademe.domain.model.Password;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

import java.util.Objects;

public final class ContractorsService {

    private final Repository<User> userRepository;
    private final NewContractorRegistrationListener newContractorRegistrationListener;

    public ContractorsService(Repository<User> userRepository) {
        this.userRepository = Objects.requireNonNull(userRepository);
        this.newContractorRegistrationListener = new NewContractorRegistrationListener();
    }

    public void save(Contractor contractor) {
        userRepository.save(contractor);
    }

    public void newContractor(ContractorRegistration contractorRegistration) {
        save(Contractor.of(
                userRepository.nextId(),
                NotEmptyString.of(contractorRegistration.getLastname()),
                NotEmptyString.of(contractorRegistration.getFirstname()),
                EmailAddress.of(contractorRegistration.getEmail()),
                Password.of(contractorRegistration.getPassword())
        ));
    }

    public NewContractorRegistrationListener getNewContractorRegistrationListener() {
        return newContractorRegistrationListener;
    }

    private class NewContractorRegistrationListener implements EventSubscriber<NewContractorRegistration> {

        private NewContractorRegistrationListener() {
        }

        @Override
        public void accept(NewContractorRegistration event) {
            newContractor(event.getContractorRegistration());
        }
    }
}
