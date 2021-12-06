package org.larrieulacoste.noe.al.trademe.features.payment.service;

import org.larrieulacoste.noe.al.trademe.features.membership_application.application.NewTradesmanApplicative;
import org.larrieulacoste.noe.al.trademe.features.payment.api.PaymentAPI;
import org.larrieulacoste.noe.al.trademe.domain.entity.User;
import org.larrieulacoste.noe.al.trademe.application.exception.PaymentException;
import org.larrieulacoste.noe.al.trademe.domain.logger.Logger;
import org.larrieulacoste.noe.al.trademe.domain.logger.LoggerFactory;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

import java.util.Objects;

public final class PaymentService implements EventSubscriber<NewTradesmanApplicative> {
    private final Logger logger;
    private final PaymentAPI paymentAPI;

    public PaymentService(LoggerFactory loggerFactory, PaymentAPI paymentAPI) {
        this.logger = Objects.requireNonNull(loggerFactory).getLogger(this);
        this.paymentAPI = Objects.requireNonNull(paymentAPI);
    }

    public void processPayment(User user, Double amount) throws PaymentException {
        logger.log(String.format("Process user payment of : %s with amount %.2f", user, amount));

        Boolean paymentSuccess = paymentAPI.pay(
                Objects.requireNonNull(user).getBankAccount(),
                Double.max(Objects.requireNonNull(amount), 0)
        );

        if (Boolean.TRUE.equals(paymentSuccess)) {
            logger.log("Payment success !");
        } else {
            throw new PaymentException("Payment error for user : " + user);
        }
    }

    @Override
    public void accept(NewTradesmanApplicative newTradesmanApplicative) {
        this.processPayment(
                newTradesmanApplicative.getTradesman(),
                newTradesmanApplicative.getAmount()
        );
    }

}
