package org.larrieulacoste.noe.al.trademe.features.payment.application.command;

import org.larrieulacoste.noe.al.trademe.application.event.ContractorEventEntity;
import org.larrieulacoste.noe.al.trademe.application.event.ContractorNewSubscriptionPayment;
import org.larrieulacoste.noe.al.trademe.domain.model.Amount;
import org.larrieulacoste.noe.al.trademe.features.payment.api.PaymentAPI;
import org.larrieulacoste.noe.al.trademe.features.payment.domain.MembersSubscriptionAmount;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;
import org.larrieulacoste.noe.al.trademe.kernel.logger.Logger;
import org.larrieulacoste.noe.al.trademe.kernel.logger.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.util.Objects;

@ApplicationScoped
public class ContractorSubscriptionPaymentService implements CommandHandler<ContractorSubscriptionPayment, Void> {
    private final Logger logger;
    private final PaymentAPI paymentAPI;
    private final EventBus<ApplicationEvent> eventBus;
    private final Amount subscriptionAmount;

    public ContractorSubscriptionPaymentService(PaymentAPI paymentAPI, EventBus<ApplicationEvent> eventBus, MembersSubscriptionAmount membersSubscriptionAmount) {
        this.logger = LoggerFactory.getLogger(this);
        this.paymentAPI = Objects.requireNonNull(paymentAPI);
        this.eventBus = eventBus;
        this.subscriptionAmount = membersSubscriptionAmount.getContractorSubscriptionAmount();
    }


    @Override
    public Void handle(ContractorSubscriptionPayment contractorSubscriptionPayment) {
        logger.log(String.format("Process contractor payment subscription of : %s with %sf", contractorSubscriptionPayment.contractorId, contractorSubscriptionPayment.paymentMethod));
        paymentAPI.pay(null, subscriptionAmount.getValue());
        eventBus.publish(ContractorNewSubscriptionPayment.withContractorAndAmount(ContractorEventEntity.withEntityIdOnly(contractorSubscriptionPayment.contractorId), subscriptionAmount));
        return null;
    }
}

