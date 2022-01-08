package org.larrieulacoste.noe.al.trademe.features.members.application.command;

import org.larrieulacoste.noe.al.trademe.application.event.ContractorDeleted;
import org.larrieulacoste.noe.al.trademe.application.event.ContractorEventEntity;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Contractor;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Contractors;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;

import javax.enterprise.context.ApplicationScoped;
import java.util.Objects;

@ApplicationScoped
public class DeleteContractorService implements CommandHandler<DeleteContractor, Void> {
    private final Contractors contractors;
    private final EventBus<ApplicationEvent> eventBus;

    DeleteContractorService(Contractors contractors, EventBus<ApplicationEvent> eventBus) {
        this.contractors = Objects.requireNonNull(contractors);
        this.eventBus = eventBus;
    }

    @Override
    public Void handle(DeleteContractor command) {
        Contractor contractor = contractors.byId(EntityId.of(command.contractorId));
        contractors.remove(contractor);
        eventBus.publish(ContractorDeleted.withContractor(
                ContractorEventEntity.withEntityIdOnly(contractor.entityId)
        ));
        return null;
    }
}
