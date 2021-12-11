package org.larrieulacoste.noe.al.trademe.features.members.service;

import org.larrieulacoste.noe.al.trademe.domain.Repository;
import org.larrieulacoste.noe.al.trademe.domain.entity.Tradesman;
import org.larrieulacoste.noe.al.trademe.domain.entity.User;
import org.larrieulacoste.noe.al.trademe.features.membership_application.application.NewTradesmanApplicative;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

public class TradesmenService {
    private final Repository<User> userRepository;
    private final NewTradesmanApplicativeListener newContractorApplicativeListener;

    public TradesmenService(Repository<User> userRepository) {
        this.userRepository = userRepository;
        this.newContractorApplicativeListener = new NewTradesmanApplicativeListener();
    }

    public void save(Tradesman tradesman) {
        userRepository.save(tradesman);
    }

    public NewTradesmanApplicativeListener getNewContractorApplicativeListener() {
        return newContractorApplicativeListener;
    }

    private class NewTradesmanApplicativeListener implements EventSubscriber<NewTradesmanApplicative> {
        private NewTradesmanApplicativeListener() {
        }

        @Override
        public void accept(NewTradesmanApplicative event) {
            save(event.getTradesman());
        }
    }
}
