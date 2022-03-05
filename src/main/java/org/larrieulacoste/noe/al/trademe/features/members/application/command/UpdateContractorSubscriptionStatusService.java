package org.larrieulacoste.noe.al.trademe.features.members.application.command;

import org.larrieulacoste.noe.al.trademe.domain.event.ContractorUpdated;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Contractor;
import org.larrieulacoste.noe.al.trademe.features.members.domain.ContractorBuilder;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Contractors;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;

import javax.enterprise.context.ApplicationScoped;
import java.util.Objects;

@ApplicationScoped
public class UpdateContractorSubscriptionStatusService
        implements CommandHandler<UpdateContractorSubscriptionStatus, Void> {
    private final Contractors contractors;
    private final EventBus<ApplicationEvent> eventBus;
    private final ContractorBuilder contractorBuilder;

    UpdateContractorSubscriptionStatusService(Contractors contractors, EventBus<ApplicationEvent> eventBus,
                                              ContractorBuilder contractorBuilder) {
        this.contractors = Objects.requireNonNull(contractors);
        this.eventBus = eventBus;
        this.contractorBuilder = Objects.requireNonNull(contractorBuilder);
    }

    @Override
    public Void handle(UpdateContractorSubscriptionStatus command) {
        Contractor contractor = contractors.byId(command.contractorId());
        contractorBuilder.clear();
        contractorBuilder.withContractor(contractor).withSubscriptionStatus(command.subscriptionStatus());

        Contractor updatedContractor = contractorBuilder.build(contractor.entityId());

        contractors.save(updatedContractor);
        eventBus.publish(ContractorUpdated.of(
                contractor.entityId(),
                contractor.firstname().value(),
                contractor.lastname().value(),
                contractor.email().value(),
                contractor.paymentMethod()
        ));
        return null;
    }
}
