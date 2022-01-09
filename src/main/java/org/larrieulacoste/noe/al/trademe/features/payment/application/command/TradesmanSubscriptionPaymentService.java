package org.larrieulacoste.noe.al.trademe.features.payment.application.command;

import org.larrieulacoste.noe.al.trademe.application.event.TradesmanEventEntity;
import org.larrieulacoste.noe.al.trademe.application.event.TradesmanNewSubscriptionPayment;
import org.larrieulacoste.noe.al.trademe.domain.model.Amount;
import org.larrieulacoste.noe.al.trademe.features.payment.api.PaymentAPI;
import org.larrieulacoste.noe.al.trademe.features.payment.domain.MembersSubscriptionAmount;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;
import org.larrieulacoste.noe.al.trademe.kernel.logger.Logger;

import javax.enterprise.context.ApplicationScoped;
import java.util.Objects;

@ApplicationScoped
public class TradesmanSubscriptionPaymentService implements CommandHandler<TradesmanSubscriptionPayment, Void> {
    private final Logger logger;
    private final PaymentAPI paymentAPI;
    private final EventBus<ApplicationEvent> eventBus;
    private final Amount subscriptionAmount;

    TradesmanSubscriptionPaymentService(Logger logger, PaymentAPI paymentAPI, EventBus<ApplicationEvent> eventBus, MembersSubscriptionAmount membersSubscriptionAmount) {
        this.logger = logger;
        this.paymentAPI = Objects.requireNonNull(paymentAPI);
        this.eventBus = eventBus;
        this.subscriptionAmount = membersSubscriptionAmount.tradesmanSubscriptionAmount;
    }


    @Override
    public Void handle(TradesmanSubscriptionPayment tradesmanSubscriptionPayment) {
        logger.log(String.format("Process tradesman payment subscription of : %s with %sf", tradesmanSubscriptionPayment.tradesmanId, tradesmanSubscriptionPayment.paymentMethod));
        paymentAPI.pay(tradesmanSubscriptionPayment.paymentMethod, subscriptionAmount.value);
        eventBus.publish(TradesmanNewSubscriptionPayment.of(TradesmanEventEntity.withEntityIdOnly(tradesmanSubscriptionPayment.tradesmanId),
                tradesmanSubscriptionPayment.paymentMethod, subscriptionAmount));
        return null;
    }
}

