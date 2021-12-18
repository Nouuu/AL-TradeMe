package org.larrieulacoste.noe.al.trademe.features.member_registration.application;

import org.larrieulacoste.noe.al.trademe.application.event.NewContractorRegistration;
import org.larrieulacoste.noe.al.trademe.application.event.NewTradesmanRegistration;
import org.larrieulacoste.noe.al.trademe.application.exception.InvalidUserException;
import org.larrieulacoste.noe.al.trademe.domain.logger.Logger;
import org.larrieulacoste.noe.al.trademe.domain.logger.LoggerFactory;
import org.larrieulacoste.noe.al.trademe.application.event.ContractorEventEntity;
import org.larrieulacoste.noe.al.trademe.application.event.TradesmanEventEntity;
import org.larrieulacoste.noe.al.trademe.features.member_validation.application.MemberValidationService;
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

    public void registerNewMember(TradesmanEventEntity tradesmanEventEntity) {
        logger.log("New member registration : " + tradesmanEventEntity);
        List<String> fieldErrors = memberValidationService.getMemberInvalidFields(tradesmanEventEntity);
        if (!fieldErrors.isEmpty()) {
            throw new InvalidUserException("Invalid tradesman registration on : " +
                    String.join(", ", fieldErrors));
        }
        eventBus.publish(NewTradesmanRegistration.of(tradesmanEventEntity));
    }

    public void registerNewMember(ContractorEventEntity contractorEventEntity) {
        logger.log("New member registration : " + contractorEventEntity);
        List<String> fieldErrors = memberValidationService.getMemberInvalidFields(contractorEventEntity);
        if (!fieldErrors.isEmpty()) {
            throw new InvalidUserException("Invalid contractor registration on : " +
                    String.join(", ", fieldErrors));
        }
        eventBus.publish(NewContractorRegistration.of(contractorEventEntity));
    }
}
