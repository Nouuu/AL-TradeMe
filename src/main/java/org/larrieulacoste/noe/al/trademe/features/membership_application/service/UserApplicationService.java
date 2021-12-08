package org.larrieulacoste.noe.al.trademe.features.membership_application.service;

import org.larrieulacoste.noe.al.trademe.domain.entity.Contractor;
import org.larrieulacoste.noe.al.trademe.domain.entity.Tradesman;
import org.larrieulacoste.noe.al.trademe.features.membership_application.application.NewContractorApplicative;
import org.larrieulacoste.noe.al.trademe.features.membership_application.application.NewTradesmanApplicative;
import org.larrieulacoste.noe.al.trademe.application.exception.UserInvalidException;
import org.larrieulacoste.noe.al.trademe.domain.logger.Logger;
import org.larrieulacoste.noe.al.trademe.domain.logger.LoggerFactory;
import org.larrieulacoste.noe.al.trademe.features.members.domain.UserRepository;
import org.larrieulacoste.noe.al.trademe.features.membership_validation.UserValidationService;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;

import java.util.Objects;


public final class UserApplicationService {
    private final EventBus<ApplicationEvent> eventBus;
    private final Logger logger;
    private final UserRepository userRepository;
    private final UserValidationService userValidationService;

    public UserApplicationService(EventBus<ApplicationEvent> eventBus, LoggerFactory loggerFactory, UserRepository userRepository, UserValidationService userValidationService) {
        this.eventBus = Objects.requireNonNull(eventBus);
        this.logger = Objects.requireNonNull(loggerFactory).getLogger(this);
        this.userRepository = Objects.requireNonNull(userRepository);
        this.userValidationService = Objects.requireNonNull(userValidationService);
    }

    public void applyForMembership(Tradesman tradesman, Double amount) throws UserInvalidException {
        logger.log("Apply for tradesman membership : " + tradesman);

        if (Boolean.TRUE.equals(userValidationService.isUserValid(tradesman))) {
            userRepository.save(tradesman);
            eventBus.publish(NewTradesmanApplicative.withTradesmanAndAmount(tradesman, amount));
        } else {
            throw new UserInvalidException("Invalid user : " + tradesman);
        }
    }

    public void applyForMembership(Contractor contractor, Double amount) throws UserInvalidException {
        logger.log("Apply for contractor membership : " + contractor);

        if (Boolean.TRUE.equals(userValidationService.isUserValid(contractor))) {
            userRepository.save(contractor);
            eventBus.publish(NewContractorApplicative.withContractorAndAmount(contractor, amount));
        } else {
            throw new UserInvalidException("Invalid user : " + contractor);
        }
    }
}
