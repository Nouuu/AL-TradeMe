package org.larrieulacoste.noe.al.trademe.features.members.service;

import org.larrieulacoste.noe.al.trademe.domain.entity.Contractor;
import org.larrieulacoste.noe.al.trademe.features.members.domain.UserRepository;
import org.larrieulacoste.noe.al.trademe.features.membership_application.application.NewContractorApplicative;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

import java.util.Objects;

public class ContractorsService {

    private final UserRepository userRepository;
    private final NewContractorApplicativeListener newContractorApplicativeListener;

    public ContractorsService(UserRepository userRepository) {
        this.userRepository = Objects.requireNonNull(userRepository);
        this.newContractorApplicativeListener = new NewContractorApplicativeListener();
    }

    public void save(Contractor contractor) {
        userRepository.save(contractor);
    }

    public NewContractorApplicativeListener getNewContractorApplicativeListener() {
        return newContractorApplicativeListener;
    }

    private class NewContractorApplicativeListener implements EventSubscriber<NewContractorApplicative> {

        private NewContractorApplicativeListener() {
        }

        @Override
        public void accept(NewContractorApplicative event) {
            save(event.getContractor());
        }
    }
}
