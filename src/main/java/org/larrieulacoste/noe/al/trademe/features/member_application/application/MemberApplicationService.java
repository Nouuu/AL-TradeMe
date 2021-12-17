package org.larrieulacoste.noe.al.trademe.features.member_application.application;

import org.larrieulacoste.noe.al.trademe.application.event.NewContractorApplicative;
import org.larrieulacoste.noe.al.trademe.application.event.NewTradesmanApplicative;
import org.larrieulacoste.noe.al.trademe.application.exception.InvalidUserException;
import org.larrieulacoste.noe.al.trademe.domain.entity.Contractor;
import org.larrieulacoste.noe.al.trademe.domain.entity.Tradesman;
import org.larrieulacoste.noe.al.trademe.domain.logger.Logger;
import org.larrieulacoste.noe.al.trademe.domain.logger.LoggerFactory;
import org.larrieulacoste.noe.al.trademe.features.member_validation.application.MemberValidationService;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;

import java.util.Objects;


public final class MemberApplicationService {
    private final EventBus<ApplicationEvent> eventBus;
    private final Logger logger;
    private final MemberValidationService memberValidationService;

    public MemberApplicationService(EventBus<ApplicationEvent> eventBus, LoggerFactory loggerFactory, MemberValidationService memberValidationService) {
        this.eventBus = Objects.requireNonNull(eventBus);
        this.logger = Objects.requireNonNull(loggerFactory).getLogger(this);
        this.memberValidationService = Objects.requireNonNull(memberValidationService);
    }

    public void applyForMembership(Tradesman tradesman, Double amount) throws InvalidUserException {
        logger.log("Apply for tradesman membership : " + tradesman);

        if (Boolean.TRUE.equals(memberValidationService.isUserValid(tradesman))) {
            eventBus.publish(NewTradesmanApplicative.withTradesmanAndAmount(tradesman, amount));
        } else {
            throw new InvalidUserException("Invalid user : " + tradesman);
        }
    }

    public void applyForMembership(Contractor contractor, Double amount) throws InvalidUserException {
        logger.log("Apply for contractor membership : " + contractor);

        if (Boolean.TRUE.equals(memberValidationService.isUserValid(contractor))) {
            eventBus.publish(NewContractorApplicative.withContractorAndAmount(contractor, amount));
        } else {
            throw new InvalidUserException("Invalid user : " + contractor);
        }
    }
}
