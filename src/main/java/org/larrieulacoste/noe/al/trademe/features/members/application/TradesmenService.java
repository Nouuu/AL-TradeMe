package org.larrieulacoste.noe.al.trademe.features.members.application;

import org.larrieulacoste.noe.al.trademe.domain.Repository;
import org.larrieulacoste.noe.al.trademe.features.members.domain.User;
import org.larrieulacoste.noe.al.trademe.application.event.TradesmanEventEntity;
import org.larrieulacoste.noe.al.trademe.domain.model.EmailAddress;
import org.larrieulacoste.noe.al.trademe.domain.model.NotEmptyString;
import org.larrieulacoste.noe.al.trademe.domain.model.Password;

public class TradesmenService {
    private final Repository<User> userRepository;

    public TradesmenService(Repository<User> userRepository) {
        this.userRepository = userRepository;
    }

    public void save(org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesman tradesman) {
        userRepository.save(tradesman);
    }

    public void newTradesman(TradesmanEventEntity tradesmanEventEntity) {
        save(org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesman.of(
                userRepository.nextId(),
                NotEmptyString.of(tradesmanEventEntity.lastname),
                NotEmptyString.of(tradesmanEventEntity.firstname),
                EmailAddress.of(tradesmanEventEntity.email),
                Password.of(tradesmanEventEntity.password)
        ));
    }
}
