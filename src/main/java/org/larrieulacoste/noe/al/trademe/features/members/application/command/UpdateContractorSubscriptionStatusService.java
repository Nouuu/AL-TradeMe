package org.larrieulacoste.noe.al.trademe.features.members.application.command;

import org.larrieulacoste.noe.al.trademe.application.event.ContractorEventEntity;
import org.larrieulacoste.noe.al.trademe.application.event.ContractorUpdated;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Contractor;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Contractors;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;

import javax.enterprise.context.ApplicationScoped;
import java.util.Objects;

@ApplicationScoped
public class UpdateContractorSubscriptionStatusService implements CommandHandler<UpdateContractorSubscriptionStatus, Void> {
    private final Contractors contractors;
    private final EventBus<ApplicationEvent> eventBus;

    public UpdateContractorSubscriptionStatusService(Contractors contractors, EventBus<ApplicationEvent> eventBus) {
        this.contractors = Objects.requireNonNull(contractors);
        this.eventBus = eventBus;
    }

    @Override
    public Void handle(UpdateContractorSubscriptionStatus command) {
        Contractor contractor = contractors.byId(command.contractorId);

        Contractor updatedContractor = Contractor.of(
                contractor.getEntityId(),
                contractor.getLastname(),
                contractor.getFirstname(),
                contractor.getEmail(),
                contractor.getPassword(),
                command.subscriptionStatus
        );

        contractors.save(updatedContractor);
        eventBus.publish(ContractorUpdated.withContractor(ContractorEventEntity.of(
                contractor.getEntityId(),
                contractor.getLastname().getField(),
                contractor.getFirstname().getField(),
                contractor.getEmail().getEmailAddressString(),
                contractor.getPassword().getPasswordString()
        )));
        return null;
    }
}
