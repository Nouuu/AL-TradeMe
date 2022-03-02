package org.larrieulacoste.noe.al.trademe.features.members.application.command;

import org.larrieulacoste.noe.al.trademe.domain.event.ContractorUpdated;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.EntityId;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Contractor;
import org.larrieulacoste.noe.al.trademe.features.members.domain.ContractorBuilder;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Contractors;
import org.larrieulacoste.noe.al.trademe.features.members.domain.MemberValidationService;
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
    private final ContractorBuilder contractorBuilder;

    UpdateContractorService(Contractors contractors, MemberValidationService memberValidationService,
                            EventBus<ApplicationEvent> eventBus, StringValidators stringValidators,
                            ContractorBuilder contractorBuilder) {
        this.contractors = Objects.requireNonNull(contractors);
        this.memberValidationService = memberValidationService;
        this.eventBus = eventBus;
        this.contractorBuilder = contractorBuilder;
    }

    @Override
    public Contractor handle(UpdateContractor updateContractor) {
        Contractor inMemoryContractor = contractors.byId(EntityId.of(updateContractor.contractorId()));

        memberValidationService.validateUpdateContractor(updateContractor);

        contractorBuilder.clear();
        contractorBuilder.withContractor(inMemoryContractor);
        if (updateContractor.lastname() != null) {
            contractorBuilder.withLastname(updateContractor.lastname());
        }
        if (updateContractor.firstname() != null) {
            contractorBuilder.withFirstname(updateContractor.firstname());
        }
        if (updateContractor.email() != null) {
            contractorBuilder.withEmail(updateContractor.email());
        }
        if (updateContractor.password() != null) {
            contractorBuilder.withPassword(updateContractor.password());
        }

        Contractor updatedContractor = contractorBuilder.build(inMemoryContractor.entityId());
        contractors.save(updatedContractor);

        eventBus.publish(
                ContractorUpdated.of(
                        updatedContractor.entityId(),
                        updatedContractor.firstname().value(),
                        updatedContractor.lastname().value(),
                        updatedContractor.email().value(),
                        updatedContractor.paymentMethod()
                ));
        return updatedContractor;
    }
}
