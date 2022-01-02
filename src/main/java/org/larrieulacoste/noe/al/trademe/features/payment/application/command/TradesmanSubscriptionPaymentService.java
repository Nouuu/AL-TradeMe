package org.larrieulacoste.noe.al.trademe.features.payment.application.command;

import org.larrieulacoste.noe.al.trademe.application.event.NewTradesmanSubscriptionPayment;
import org.larrieulacoste.noe.al.trademe.application.event.TradesmanEventEntity;
import org.larrieulacoste.noe.al.trademe.domain.model.Amount;
import org.larrieulacoste.noe.al.trademe.features.payment.api.PaymentAPI;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;
import org.larrieulacoste.noe.al.trademe.kernel.logger.Logger;
import org.larrieulacoste.noe.al.trademe.kernel.logger.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.util.Objects;

@ApplicationScoped
public class TradesmanSubscriptionPaymentService implements CommandHandler<TradesmanSubscriptionPayment, Void> {
    private final Logger logger;
    private final PaymentAPI paymentAPI;
    private final EventBus<ApplicationEvent> eventBus;
    private final Amount subscriptionAmount = Amount.of(0.);

    public TradesmanSubscriptionPaymentService(PaymentAPI paymentAPI, EventBus<ApplicationEvent> eventBus) {
        this.logger = LoggerFactory.getLogger(this);
        this.paymentAPI = Objects.requireNonNull(paymentAPI);
        this.eventBus = eventBus;
    }


    @Override
    public Void handle(TradesmanSubscriptionPayment tradesmanSubscriptionPayment) {
        logger.log(String.format("Process tradesman payment subscription of : %s with %sf", tradesmanSubscriptionPayment.tradesmanId, tradesmanSubscriptionPayment.paymentMethod));
        paymentAPI.pay(null, subscriptionAmount.getValue());
        eventBus.publish(NewTradesmanSubscriptionPayment.withTradesmanAndAmount(TradesmanEventEntity.withEntityIdOnly(tradesmanSubscriptionPayment.tradesmanId), subscriptionAmount));
        return null;
    }
}

