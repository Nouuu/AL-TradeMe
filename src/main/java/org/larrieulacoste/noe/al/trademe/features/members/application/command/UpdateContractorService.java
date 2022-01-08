package org.larrieulacoste.noe.al.trademe.features.members.application.command;

import org.larrieulacoste.noe.al.trademe.application.event.ContractorEventEntity;
import org.larrieulacoste.noe.al.trademe.application.event.ContractorUpdated;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.features.members.application.MemberValidationService;
import org.larrieulacoste.noe.al.trademe.features.members.domain.*;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;

import javax.enterprise.context.ApplicationScoped;
import java.util.Objects;

@ApplicationScoped
public class UpdateContractorService implements CommandHandler<UpdateContractor, Contractor> {
    private final Contractors contractors;
    private final MemberValidationService memberValidationService;
    private final EventBus<ApplicationEvent> eventBus;

    UpdateContractorService(Contractors contractors, MemberValidationService memberValidationService, EventBus<ApplicationEvent> eventBus) {
        this.contractors = Objects.requireNonNull(contractors);
        this.memberValidationService = memberValidationService;
        this.eventBus = eventBus;
    }

    @Override
    public Contractor handle(UpdateContractor updateContractor) {
        Contractor inMemoryContractor = contractors.byId(EntityId.of(updateContractor.contractorId));

        memberValidationService.validateUpdateContractor(updateContractor);

        Contractor updatedContractor = Contractor.of(
                inMemoryContractor.getEntityId(),
                updateContractor.lastname != null ? NotEmptyString.of(updateContractor.lastname) : inMemoryContractor.getLastname(),
                updateContractor.firstname != null ? NotEmptyString.of(updateContractor.firstname) : inMemoryContractor.getLastname(),
                updateContractor.email != null ? EmailAddress.of(updateContractor.email) : inMemoryContractor.getEmail(),
                updateContractor.password != null ? Password.of(updateContractor.password) : inMemoryContractor.getPassword(),
                inMemoryContractor.getSubscriptionStatus(),
                inMemoryContractor.getPaymentMethod());
        contractors.save(updatedContractor);

        eventBus.publish(ContractorUpdated.withContractor(ContractorEventEntity.withoutPassword(inMemoryContractor.getEntityId(),
                updateContractor.firstname, updateContractor.lastname, updateContractor.email, inMemoryContractor.getPaymentMethod())));
        return updatedContractor;
    }
}
