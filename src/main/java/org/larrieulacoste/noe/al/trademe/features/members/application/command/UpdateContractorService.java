package org.larrieulacoste.noe.al.trademe.features.members.application.command;

import org.larrieulacoste.noe.al.trademe.application.event.ContractorEventEntity;
import org.larrieulacoste.noe.al.trademe.application.event.ContractorUpdated;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.features.members.domain.*;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;
import org.larrieulacoste.noe.al.trademe.kernel.validators.StringValidators;

import javax.enterprise.context.ApplicationScoped;
import java.util.Objects;

@ApplicationScoped
public class UpdateContractorService implements CommandHandler<UpdateContractor, Contractor> {
    private final Contractors contractors;
    private final MemberValidationService memberValidationService;
    private final EventBus<ApplicationEvent> eventBus;
    private final StringValidators stringValidators;

    UpdateContractorService(Contractors contractors, MemberValidationService memberValidationService, EventBus<ApplicationEvent> eventBus, StringValidators stringValidators) {
        this.contractors = Objects.requireNonNull(contractors);
        this.memberValidationService = memberValidationService;
        this.eventBus = eventBus;
        this.stringValidators = stringValidators;
    }

    @Override
    public Contractor handle(UpdateContractor updateContractor) {
        Contractor inMemoryContractor = contractors.byId(EntityId.of(updateContractor.contractorId()));

        memberValidationService.validateUpdateContractor(updateContractor);

        Contractor updatedContractor = Contractor.of(
                inMemoryContractor.entityId(),
                updateContractor.lastname() != null ? NotEmptyString.of(updateContractor.lastname(), stringValidators) : inMemoryContractor.lastname(),
                updateContractor.firstname() != null ? NotEmptyString.of(updateContractor.firstname(), stringValidators) : inMemoryContractor.lastname(),
                updateContractor.email() != null ? EmailAddress.of(updateContractor.email(), stringValidators) : inMemoryContractor.email(),
                updateContractor.password() != null ? Password.of(updateContractor.password(), stringValidators) : inMemoryContractor.password(),
                inMemoryContractor.subscriptionStatus(),
                inMemoryContractor.paymentMethod());
        contractors.save(updatedContractor);

        eventBus.publish(ContractorUpdated.withContractor(ContractorEventEntity.withoutPassword(inMemoryContractor.entityId(),
                updateContractor.firstname(), updateContractor.lastname(), updateContractor.email(), inMemoryContractor.paymentMethod())));
        return updatedContractor;
    }
}
