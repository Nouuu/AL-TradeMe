package org.larrieulacoste.noe.al.trademe.features.members.application.command;

import org.larrieulacoste.noe.al.trademe.application.event.ContractorEventEntity;
import org.larrieulacoste.noe.al.trademe.application.event.ContractorRegistered;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.domain.model.PaymentMethod;
import org.larrieulacoste.noe.al.trademe.features.members.domain.*;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;
import org.larrieulacoste.noe.al.trademe.kernel.validators.StringValidators;

import javax.enterprise.context.ApplicationScoped;
import java.util.Objects;

@ApplicationScoped
public class CreateContractorService implements CommandHandler<CreateContractor, EntityId> {
    private final Contractors contractors;
    private final MemberValidationService memberValidationService;
    private final EventBus<ApplicationEvent> eventBus;
    private final StringValidators stringValidators;

    CreateContractorService(Contractors contractors, MemberValidationService memberValidationService, EventBus<ApplicationEvent> eventBus, StringValidators stringValidators) {
        this.contractors = Objects.requireNonNull(contractors);
        this.memberValidationService = memberValidationService;
        this.eventBus = eventBus;
        this.stringValidators = stringValidators;
    }

    @Override
    public EntityId handle(CreateContractor createContractor) {
        memberValidationService.validateCreateContractor(createContractor);

        final EntityId userId = contractors.nextId();
        Contractor contractor = Contractor.of(
                userId,
                NotEmptyString.of(createContractor.lastname(), stringValidators),
                NotEmptyString.of(createContractor.firstname(), stringValidators),
                EmailAddress.of(createContractor.email(), stringValidators),
                Password.of(createContractor.password(), stringValidators),
                SubscriptionStatus.PENDING_PAYMENT,
                PaymentMethod.of(createContractor.paymentMethodType(), createContractor.paymentMethodRessource())
        );
        contractors.save(contractor);

        eventBus.publish(ContractorRegistered.withContractor(ContractorEventEntity.withoutPassword(userId, createContractor.firstname(),
                createContractor.lastname(), createContractor.email(), PaymentMethod.of(createContractor.paymentMethodType(), createContractor.paymentMethodRessource()))));

        return userId;
    }
}
