package org.larrieulacoste.noe.al.trademe.features.payment.application.command;

import org.larrieulacoste.noe.al.trademe.domain.event.ContractorNewSubscriptionPayment;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.Amount;
import org.larrieulacoste.noe.al.trademe.features.payment.api.PaymentAPI;
import org.larrieulacoste.noe.al.trademe.features.payment.domain.MembersSubscriptionAmount;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;
import org.larrieulacoste.noe.al.trademe.kernel.logger.Logger;

import javax.enterprise.context.ApplicationScoped;
import java.util.Objects;

@ApplicationScoped
public class ContractorSubscriptionPaymentService implements CommandHandler<ContractorSubscriptionPayment, Void> {
    private final Logger logger;
    private final PaymentAPI paymentAPI;
    private final EventBus<ApplicationEvent> eventBus;
    private final Amount subscriptionAmount;

    ContractorSubscriptionPaymentService(Logger logger, PaymentAPI paymentAPI, EventBus<ApplicationEvent> eventBus, MembersSubscriptionAmount membersSubscriptionAmount) {
        this.logger = logger;
        this.paymentAPI = Objects.requireNonNull(paymentAPI);
        this.eventBus = eventBus;
        this.subscriptionAmount = membersSubscriptionAmount.contractorSubscriptionAmount();
    }


    @Override
    public Void handle(ContractorSubscriptionPayment contractorSubscriptionPayment) {
        logger.log(String.format("Process contractor payment subscription of : %s with %sf", contractorSubscriptionPayment.contractorId(), contractorSubscriptionPayment.paymentMethod()));
        paymentAPI.pay(contractorSubscriptionPayment.paymentMethod(), subscriptionAmount.value());
        eventBus.publish(ContractorNewSubscriptionPayment.of(
                contractorSubscriptionPayment.contractorId(),
                contractorSubscriptionPayment.paymentMethod(),
                subscriptionAmount
        ));
        return null;
    }
}

