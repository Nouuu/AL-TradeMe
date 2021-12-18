package org.larrieulacoste.noe.al.trademe.features.members.application;

import org.larrieulacoste.noe.al.trademe.domain.Repository;
import org.larrieulacoste.noe.al.trademe.features.members.domain.User;
import org.larrieulacoste.noe.al.trademe.application.event.ContractorEventEntity;
import org.larrieulacoste.noe.al.trademe.domain.model.EmailAddress;
import org.larrieulacoste.noe.al.trademe.domain.model.NotEmptyString;
import org.larrieulacoste.noe.al.trademe.domain.model.Password;

import java.util.Objects;

public final class ContractorsService {

    private final Repository<User> userRepository;

    public ContractorsService(Repository<User> userRepository) {
        this.userRepository = Objects.requireNonNull(userRepository);
    }

    public void save(org.larrieulacoste.noe.al.trademe.features.members.domain.Contractor contractor) {
        userRepository.save(contractor);
    }

    public void newContractor(ContractorEventEntity contractorEventEntity) {
        save(org.larrieulacoste.noe.al.trademe.features.members.domain.Contractor.of(
                userRepository.nextId(),
                NotEmptyString.of(contractorEventEntity.firstname),
                NotEmptyString.of(contractorEventEntity.lastname),
                EmailAddress.of(contractorEventEntity.email),
                Password.of(contractorEventEntity.password)
        ));
    }
}
