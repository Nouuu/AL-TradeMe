package org.larrieulacoste.noe.al.trademe.features.payment.application;

import org.larrieulacoste.noe.al.trademe.application.event.NewTradesmanPayment;
import org.larrieulacoste.noe.al.trademe.application.event.TradesmanEventEntity;
import org.larrieulacoste.noe.al.trademe.domain.logger.Logger;
import org.larrieulacoste.noe.al.trademe.domain.logger.LoggerFactory;
import org.larrieulacoste.noe.al.trademe.features.payment.api.PaymentAPI;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;

import javax.enterprise.context.ApplicationScoped;
import java.util.Objects;

@ApplicationScoped
public class TradesmanProcessPaymentService implements CommandHandler<TradesmanPayment, Void> {
    private final Logger logger;
    private final PaymentAPI paymentAPI;
    private final EventBus<ApplicationEvent> eventBus;

    public TradesmanProcessPaymentService(LoggerFactory loggerFactory, PaymentAPI paymentAPI, EventBus<ApplicationEvent> eventBus) {
        this.logger = Objects.requireNonNull(loggerFactory).getLogger(this);
        this.paymentAPI = Objects.requireNonNull(paymentAPI);
        this.eventBus = eventBus;
    }


    @Override
    public Void handle(TradesmanPayment tradesmanPayment) {
        logger.log(String.format("Process tradesman payment of : %s with %sf", tradesmanPayment.tradesmanId, tradesmanPayment.paymentMethod));

        /* Boolean paymentSuccess = paymentAPI.pay(
                "TODO",
                Double.max(Objects.requireNonNull(amount), 0)
        );

        if (Boolean.TRUE.equals(paymentSuccess)) {
            logger.log("Payment success !");
        } else {
            throw new PaymentException("Payment error for user : " + user);
        } */
        eventBus.publish(NewTradesmanPayment.withTradesman(new TradesmanEventEntity(tradesmanPayment.tradesmanId)));
        return null;
    }
}

