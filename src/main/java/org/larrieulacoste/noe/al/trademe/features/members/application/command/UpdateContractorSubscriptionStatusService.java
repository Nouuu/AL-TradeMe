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

    UpdateContractorSubscriptionStatusService(Contractors contractors, EventBus<ApplicationEvent> eventBus) {
        this.contractors = Objects.requireNonNull(contractors);
        this.eventBus = eventBus;
    }

    @Override
    public Void handle(UpdateContractorSubscriptionStatus command) {
        Contractor contractor = contractors.byId(command.contractorId);

        Contractor updatedContractor = Contractor.of(
                contractor.entityId,
                contractor.lastname,
                contractor.firstname,
                contractor.email,
                contractor.password,
                command.subscriptionStatus,
                contractor.paymentMethod
        );

        contractors.save(updatedContractor);
        eventBus.publish(ContractorUpdated.withContractor(ContractorEventEntity.of(
                contractor.entityId,
                contractor.lastname.value,
                contractor.firstname.value,
                contractor.email.value,
                contractor.password.value,
                contractor.paymentMethod
        )));
        return null;
    }
}
