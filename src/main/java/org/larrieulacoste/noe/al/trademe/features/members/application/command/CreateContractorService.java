package org.larrieulacoste.noe.al.trademe.features.members.application.command;

import org.larrieulacoste.noe.al.trademe.application.event.ContractorEventEntity;
import org.larrieulacoste.noe.al.trademe.application.event.NewContractorRegistered;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.features.members.application.MemberValidationService;
import org.larrieulacoste.noe.al.trademe.features.members.domain.*;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;

import javax.enterprise.context.ApplicationScoped;
import java.util.Objects;

@ApplicationScoped
public class CreateContractorService implements CommandHandler<CreateContractor, EntityId> {
    private final Contractors contractors;
    private final MemberValidationService memberValidationService;
    private final EventBus<ApplicationEvent> eventBus;

    public CreateContractorService(Contractors contractors, MemberValidationService memberValidationService, EventBus<ApplicationEvent> eventBus) {
        this.contractors = Objects.requireNonNull(contractors);
        this.memberValidationService = memberValidationService;
        this.eventBus = eventBus;
    }

    @Override
    public EntityId handle(CreateContractor createContractor) {
        memberValidationService.validateCreateContractor(createContractor);

        final EntityId userId = contractors.nextId();
        Contractor contractor = Contractor.of(
                userId,
                NotEmptyString.of(createContractor.lastname),
                NotEmptyString.of(createContractor.firstname),
                EmailAddress.of(createContractor.email),
                Password.of(createContractor.password),
                SubscriptionStatus.PENDING_PAYMENT
        );
        contractors.save(contractor);

        eventBus.publish(NewContractorRegistered.withContractor(ContractorEventEntity.withoutPassword(userId, createContractor.firstname,
                createContractor.lastname, createContractor.email)));

        return userId;
    }
}
