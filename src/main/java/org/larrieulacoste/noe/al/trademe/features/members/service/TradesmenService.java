package org.larrieulacoste.noe.al.trademe.features.members.service;

import org.larrieulacoste.noe.al.trademe.application.event.NewTradesmanRegistration;
import org.larrieulacoste.noe.al.trademe.domain.Repository;
import org.larrieulacoste.noe.al.trademe.domain.entity.Tradesman;
import org.larrieulacoste.noe.al.trademe.domain.entity.User;
import org.larrieulacoste.noe.al.trademe.domain.model.EmailAddress;
import org.larrieulacoste.noe.al.trademe.domain.model.NotEmptyString;
import org.larrieulacoste.noe.al.trademe.domain.model.Password;
import org.larrieulacoste.noe.al.trademe.domain.model.TradesmanRegistration;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

public class TradesmenService {
    private final Repository<User> userRepository;
    private final NewTradesmanRegistrationListener newTradesmanRegistrationListener;

    public TradesmenService(Repository<User> userRepository) {
        this.userRepository = userRepository;
        this.newTradesmanRegistrationListener = new NewTradesmanRegistrationListener();
    }

    public void save(Tradesman tradesman) {
        userRepository.save(tradesman);
    }

    public void newTradesman(TradesmanRegistration tradesmanRegistration) {
        save(Tradesman.of(
                userRepository.nextId(),
                NotEmptyString.of(tradesmanRegistration.getLastname()),
                NotEmptyString.of(tradesmanRegistration.getFirstname()),
                EmailAddress.of(tradesmanRegistration.getEmail()),
                Password.of(tradesmanRegistration.getPassword())
        ));
    }

    public NewTradesmanRegistrationListener getNewTradesmanRegistrationListener() {
        return newTradesmanRegistrationListener;
    }

    private class NewTradesmanRegistrationListener implements EventSubscriber<NewTradesmanRegistration> {
        private NewTradesmanRegistrationListener() {
        }

        @Override
        public void accept(NewTradesmanRegistration event) {
            newTradesman(event.getTradesmanRegistration());
        }
    }
}
