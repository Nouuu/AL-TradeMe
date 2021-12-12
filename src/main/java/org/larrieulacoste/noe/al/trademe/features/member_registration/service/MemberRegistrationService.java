package org.larrieulacoste.noe.al.trademe.features.member_registration.service;

import org.larrieulacoste.noe.al.trademe.application.event.NewContractorRegistration;
import org.larrieulacoste.noe.al.trademe.application.event.NewTradesmanRegistration;
import org.larrieulacoste.noe.al.trademe.application.exception.InvalidUserException;
import org.larrieulacoste.noe.al.trademe.domain.logger.Logger;
import org.larrieulacoste.noe.al.trademe.domain.logger.LoggerFactory;
import org.larrieulacoste.noe.al.trademe.domain.model.ContractorRegistration;
import org.larrieulacoste.noe.al.trademe.domain.model.TradesmanRegistration;
import org.larrieulacoste.noe.al.trademe.features.member_validation.service.MemberValidationService;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;

import java.util.List;
import java.util.Objects;

public final class MemberRegistrationService {
    private final EventBus<ApplicationEvent> eventBus;
    private final Logger logger;
    private final MemberValidationService memberValidationService;

    public MemberRegistrationService(EventBus<ApplicationEvent> eventBus, LoggerFactory loggerFactory, MemberValidationService memberValidationService) {
        this.eventBus = Objects.requireNonNull(eventBus);
        this.logger = Objects.requireNonNull(loggerFactory).getLogger(this);
        this.memberValidationService = Objects.requireNonNull(memberValidationService);
    }

    public void registerNewMember(TradesmanRegistration tradesmanRegistration) {
        logger.log("New member registration : " + tradesmanRegistration);
        List<String> fieldErrors = memberValidationService.getMemberInvalidFields(tradesmanRegistration);
        if (!fieldErrors.isEmpty()) {
            throw new InvalidUserException("Invalid tradesman registration on : " +
                    String.join(", ", fieldErrors));
        }
        eventBus.publish(NewTradesmanRegistration.of(tradesmanRegistration));
    }

    public void registerNewMember(ContractorRegistration contractorRegistration) {
        logger.log("New member registration : " + contractorRegistration);
        List<String> fieldErrors = memberValidationService.getMemberInvalidFields(contractorRegistration);
        if (!fieldErrors.isEmpty()) {
            throw new InvalidUserException("Invalid contractor registration on : " +
                    String.join(", ", fieldErrors));
        }
        eventBus.publish(NewContractorRegistration.of(contractorRegistration));
    }
}
