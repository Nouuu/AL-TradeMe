package org.larrieulacoste.noe.al.trademe.domain.service;

import org.larrieulacoste.noe.al.trademe.domain.entity.User;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;
import org.larrieulacoste.noe.al.trademe.application.UserApplicationEvent;
import org.larrieulacoste.noe.al.trademe.domain.exception.UserInvalidException;
import org.larrieulacoste.noe.al.trademe.domain.logger.Logger;
import org.larrieulacoste.noe.al.trademe.domain.logger.LoggerFactory;
import org.larrieulacoste.noe.al.trademe.domain.repository.UserRepository;

import java.util.Objects;


public final class UserApplicationService {
    private final EventBus<UserApplicationEvent> eventBus;
    private final Logger logger;
    private final UserRepository userRepository;
    private final UserValidationService userValidationService;

    public UserApplicationService(EventBus<UserApplicationEvent> eventBus, LoggerFactory loggerFactory, UserRepository userRepository, UserValidationService userValidationService) {
        this.eventBus = Objects.requireNonNull(eventBus);
        this.logger = Objects.requireNonNull(loggerFactory).getLogger(this);
        this.userRepository = Objects.requireNonNull(userRepository);
        this.userValidationService = Objects.requireNonNull(userValidationService);
    }

    public void applyForMembership(User user, Double amount) throws UserInvalidException {
        logger.log("Apply for Membership : " + user);

        if (Boolean.TRUE.equals(userValidationService.isUserValid(user))) {
            userRepository.save(user);
            eventBus.send(UserApplicationEvent.withUserAndAmount(user, amount));
        } else {
            throw new UserInvalidException("Invalid user : " + user);
        }
    }
}
