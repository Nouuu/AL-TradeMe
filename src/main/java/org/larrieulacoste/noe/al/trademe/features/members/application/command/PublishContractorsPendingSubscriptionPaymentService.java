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
import java.util.stream.Collectors;

@ApplicationScoped
public class PublishContractorsPendingSubscriptionPaymentService implements CommandHandler<PublishContractorsPendingSubscriptionPayment, Void> {
    private final Contractors contractors;
    private final EventBus<ApplicationEvent> eventBus;

    public PublishContractorsPendingSubscriptionPaymentService(Contractors contractors, EventBus<ApplicationEvent> eventBus) {
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
                                .map(contractor -> ContractorEventEntity.withEntityIdOnly(contractor.getEntityId()))
                                .collect(Collectors.toList())
                )
        );
        return null;
    }

    public void updateSubscriptionToPending(Contractor contractor) {
        Contractor updatedContractor = Contractor.of(
                contractor.getEntityId(),
                contractor.getLastname(),
                contractor.getFirstname(),
                contractor.getEmail(),
                contractor.getPassword(),
                SubscriptionStatus.PENDING_PAYMENT
        );
        contractors.save(updatedContractor);
        eventBus.publish(ContractorUpdated.withContractor(ContractorEventEntity.of(
                contractor.getEntityId(),
                contractor.getLastname().getField(),
                contractor.getFirstname().getField(),
                contractor.getEmail().getEmailAddressString(),
                contractor.getPassword().getPasswordString()
        )));
    }
}
