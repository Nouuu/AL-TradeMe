package org.larrieulacoste.noe.al.trademe.features.payment.application;

import org.larrieulacoste.noe.al.trademe.application.event.ContractorEventEntity;
import org.larrieulacoste.noe.al.trademe.application.event.NewContractorPayment;
import org.larrieulacoste.noe.al.trademe.kernel.logger.Logger;
import org.larrieulacoste.noe.al.trademe.features.payment.api.PaymentAPI;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;
import org.larrieulacoste.noe.al.trademe.kernel.logger.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.util.Objects;

@ApplicationScoped
public class ContractorProcessPaymentService implements CommandHandler<ContractorPayment, Void> {
    private final Logger logger;
    private final PaymentAPI paymentAPI;
    private final EventBus<ApplicationEvent> eventBus;

    public ContractorProcessPaymentService(PaymentAPI paymentAPI, EventBus<ApplicationEvent> eventBus) {
        this.logger = LoggerFactory.getLogger(this);
        this.paymentAPI = Objects.requireNonNull(paymentAPI);
        this.eventBus = eventBus;
    }


    @Override
    public Void handle(ContractorPayment contractorPayment) {
        logger.log(String.format("Process user tradesman of : %s with %sf", contractorPayment.contractorId, contractorPayment.paymentMethod));
        paymentAPI.pay(null, 0);
        eventBus.publish(NewContractorPayment.withContractor(ContractorEventEntity.withEntityIdOnly(contractorPayment.contractorId)));
        return null;
    }
}

