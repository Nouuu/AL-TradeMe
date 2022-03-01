package org.larrieulacoste.noe.al.trademe.features.members.application.command;

import org.larrieulacoste.noe.al.trademe.domain.event.ContractorUpdated;
import org.larrieulacoste.noe.al.trademe.domain.event.ContractorsSubscriptionPendingPayment;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.MemberPayment;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Contractor;
import org.larrieulacoste.noe.al.trademe.features.members.domain.ContractorBuilder;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Contractors;
import org.larrieulacoste.noe.al.trademe.features.members.domain.SubscriptionStatus;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class PublishContractorsPendingSubscriptionPaymentService
        implements CommandHandler<PublishContractorsPendingSubscriptionPayment, Void> {
    private final Contractors contractors;
    private final EventBus<ApplicationEvent> eventBus;
    private final ContractorBuilder contractorBuilder;

    PublishContractorsPendingSubscriptionPaymentService(Contractors contractors, EventBus<ApplicationEvent> eventBus,
                                                        ContractorBuilder contractorBuilder) {
        this.contractors = Objects.requireNonNull(contractors);
        this.eventBus = eventBus;
        this.contractorBuilder = Objects.requireNonNull(contractorBuilder);
    }

    @Override
    public Void handle(PublishContractorsPendingSubscriptionPayment command) {
        List<Contractor> contractorsPendingPayment = contractors.findAll();

        contractorsPendingPayment.forEach(this::updateSubscriptionToPending);

        eventBus.publish(
                ContractorsSubscriptionPendingPayment.withContractors(
                        contractorsPendingPayment.stream()
                                .map(contractor -> MemberPayment.of(contractor.entityId(), contractor.paymentMethod()))
                                .toList()));
        return null;
    }

    public void updateSubscriptionToPending(Contractor contractor) {
        contractorBuilder.clear();
        contractorBuilder.withContractor(contractor).withSubscriptionStatus(SubscriptionStatus.PENDING_PAYMENT);
        Contractor updatedContractor = contractorBuilder.build(contractor.entityId());
        contractors.save(updatedContractor);
        eventBus.publish(ContractorUpdated.of(
                contractor.entityId(),
                contractor.firstname().value(),
                contractor.lastname().value(),
                contractor.email().value(),
                contractor.paymentMethod()
        ));
    }
}
