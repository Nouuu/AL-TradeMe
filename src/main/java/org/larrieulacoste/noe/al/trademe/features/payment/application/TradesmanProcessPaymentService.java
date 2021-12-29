package org.larrieulacoste.noe.al.trademe.features.payment.application;

import org.larrieulacoste.noe.al.trademe.application.event.NewTradesmanPayment;
import org.larrieulacoste.noe.al.trademe.application.event.TradesmanEventEntity;
import org.larrieulacoste.noe.al.trademe.features.payment.api.PaymentAPI;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;
import org.larrieulacoste.noe.al.trademe.kernel.logger.Logger;
import org.larrieulacoste.noe.al.trademe.kernel.logger.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.util.Objects;

@ApplicationScoped
public class TradesmanProcessPaymentService implements CommandHandler<TradesmanPayment, Void> {
    private final Logger logger;
    private final PaymentAPI paymentAPI;
    private final EventBus<ApplicationEvent> eventBus;

    public TradesmanProcessPaymentService(PaymentAPI paymentAPI, EventBus<ApplicationEvent> eventBus) {
        this.logger = LoggerFactory.getLogger(this);
        this.paymentAPI = Objects.requireNonNull(paymentAPI);
        this.eventBus = eventBus;
    }


    @Override
    public Void handle(TradesmanPayment tradesmanPayment) {
        logger.log(String.format("Process tradesman payment of : %s with %sf", tradesmanPayment.tradesmanId, tradesmanPayment.paymentMethod));
        paymentAPI.pay(null, 0);
        eventBus.publish(NewTradesmanPayment.withTradesman(TradesmanEventEntity.withEntityIdOnly(tradesmanPayment.tradesmanId)));
        return null;
    }
}

