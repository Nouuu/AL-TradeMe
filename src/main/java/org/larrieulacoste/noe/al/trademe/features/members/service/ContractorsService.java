package org.larrieulacoste.noe.al.trademe.features.members.service;

import org.larrieulacoste.noe.al.trademe.domain.entity.Contractor;
import org.larrieulacoste.noe.al.trademe.features.members.domain.UserRepository;
import org.larrieulacoste.noe.al.trademe.features.membership_application.application.NewContractorApplicative;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

import java.util.Objects;

public class ContractorsService implements EventSubscriber<NewContractorApplicative> {

    private final UserRepository userRepository;

    public ContractorsService(UserRepository userRepository) {
        this.userRepository = Objects.requireNonNull(userRepository);
    }

    public void save(Contractor contractor) {
        userRepository.save(contractor);
    }


    @Override
    public void accept(NewContractorApplicative event) {
        this.save(event.getContractor());
    }
}
