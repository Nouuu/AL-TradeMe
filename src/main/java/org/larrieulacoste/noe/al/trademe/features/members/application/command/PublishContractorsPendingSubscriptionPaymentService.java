package org.larrieulacoste.noe.al.trademe.features.members.application.command;

import org.larrieulacoste.noe.al.trademe.application.event.ContractorEventEntity;
import org.larrieulacoste.noe.al.trademe.application.event.ContractorUpdated;
import org.larrieulacoste.noe.al.trademe.application.event.ContractorsSubscriptionPendingPayment;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Contractor;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Contractors;
import org.larrieulacoste.noe.al.trademe.features.members.domain.SubscriptionStatus;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class PublishContractorsPendingSubscriptionPaymentService implements CommandHandler<PublishContractorsPendingSubscriptionPayment, Void> {
    private final Contractors contractors;
    private final EventBus<ApplicationEvent> eventBus;

    PublishContractorsPendingSubscriptionPaymentService(Contractors contractors, EventBus<ApplicationEvent> eventBus) {
        this.contractors = Objects.requireNonNull(contractors);
        this.eventBus = eventBus;
    }

    @Override
    public Void handle(PublishContractorsPendingSubscriptionPayment command) {
        List<Contractor> contractorsPendingPayment = contractors.findAll();

        contractorsPendingPayment.forEach(this::updateSubscriptionToPending);

        eventBus.publish(
                ContractorsSubscriptionPendingPayment.withContractors(
                        contractorsPendingPayment.stream()
                                .map(contractor -> ContractorEventEntity.withEntityIdOnly(contractor.entityId()))
                                .toList()
                )
        );
        return null;
    }

    public void updateSubscriptionToPending(Contractor contractor) {
        Contractor updatedContractor = Contractor.of(
                contractor.entityId(),
                contractor.lastname(),
                contractor.firstname(),
                contractor.email(),
                contractor.password(),
                SubscriptionStatus.PENDING_PAYMENT,
                contractor.paymentMethod()
        );
        contractors.save(updatedContractor);
        eventBus.publish(ContractorUpdated.withContractor(ContractorEventEntity.of(
                contractor.entityId(),
                contractor.lastname().value,
                contractor.firstname().value,
                contractor.email().value,
                contractor.password().value,
                contractor.paymentMethod()
        )));
    }
}
