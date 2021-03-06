package org.larrieulacoste.noe.al.trademe.features.members.application.command;

import org.larrieulacoste.noe.al.trademe.domain.event.ContractorRegistered;
import org.larrieulacoste.noe.al.trademe.features.members.domain.*;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.EntityId;

import javax.enterprise.context.ApplicationScoped;
import java.util.Objects;

@ApplicationScoped
public class CreateContractorService implements CommandHandler<CreateContractor, EntityId> {
    private final Contractors contractors;
    private final MemberValidationService memberValidationService;
    private final EventBus<ApplicationEvent> eventBus;
    private final ContractorBuilder contractorBuilder;

    CreateContractorService(Contractors contractors, MemberValidationService memberValidationService,
                            EventBus<ApplicationEvent> eventBus, ContractorBuilder contractorBuilder) {
        this.contractors = Objects.requireNonNull(contractors);
        this.memberValidationService = memberValidationService;
        this.eventBus = eventBus;
        this.contractorBuilder = contractorBuilder;
    }

    @Override
    public EntityId handle(CreateContractor createContractor) {
        memberValidationService.validateCreateContractor(createContractor);

        final EntityId userId = contractors.nextId();
        contractorBuilder.clear();
        contractorBuilder
                .withLastname(createContractor.lastname())
                .withFirstname(createContractor.firstname())
                .withEmail(createContractor.email())
                .withPassword(createContractor.password())
                .withSubscriptionStatus(SubscriptionStatus.PENDING_PAYMENT)
                .withPaymentMethod(createContractor.paymentMethodType(), createContractor.paymentMethodRessource());

        Contractor contractor = contractorBuilder.build(userId);
        contractors.save(contractor);

        eventBus.publish(ContractorRegistered.of(
                userId,
                contractor.firstname().value(),
                contractor.lastname().value(),
                contractor.email().value(),
                contractor.paymentMethod()
        ));

        return userId;
    }
}
