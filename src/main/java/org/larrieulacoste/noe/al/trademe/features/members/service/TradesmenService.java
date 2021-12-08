package org.larrieulacoste.noe.al.trademe.features.members.service;

import org.larrieulacoste.noe.al.trademe.domain.entity.Tradesman;
import org.larrieulacoste.noe.al.trademe.features.members.domain.UserRepository;
import org.larrieulacoste.noe.al.trademe.features.membership_application.application.NewTradesmanApplicative;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

public class TradesmenService implements EventSubscriber<NewTradesmanApplicative> {
    private final UserRepository userRepository;

    public TradesmenService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(Tradesman tradesman) {
        userRepository.save(tradesman);
    }


    @Override
    public void accept(NewTradesmanApplicative event) {
        this.save(event.getTradesman());
    }
}
