package org.larrieulacoste.noe.al.trademe.features.members.application;

import org.larrieulacoste.noe.al.trademe.domain.Repository;
import org.larrieulacoste.noe.al.trademe.domain.model.EmailAddress;
import org.larrieulacoste.noe.al.trademe.domain.model.NotEmptyString;
import org.larrieulacoste.noe.al.trademe.domain.model.Password;
import org.larrieulacoste.noe.al.trademe.features.members.domain.User;

public class CreateMemberService {
    private final Repository<User> userRepository;

    public CreateMemberService(Repository<User> userRepository) {
        this.userRepository = userRepository;
    }

    public void save(org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesman tradesman) {
        userRepository.save(tradesman);
    }

    public void handle(CreateTradesman createTradesman) {
        save(org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesman.of(
                userRepository.nextId(),
                NotEmptyString.of(createTradesman.lastname),
                NotEmptyString.of(createTradesman.firstname),
                EmailAddress.of(createTradesman.email),
                Password.of(createTradesman.password)
        ));
    }

    public void handle(CreateContractor createContractor) {
        save(org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesman.of(
                userRepository.nextId(),
                NotEmptyString.of(createContractor.lastname),
                NotEmptyString.of(createContractor.firstname),
                EmailAddress.of(createContractor.email),
                Password.of(createContractor.password)
        ));
    }
}
