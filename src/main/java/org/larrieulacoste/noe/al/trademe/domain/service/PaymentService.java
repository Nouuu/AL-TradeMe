package org.larrieulacoste.noe.al.trademe.domain.service;

import org.larrieulacoste.noe.al.trademe.domain.api.PaymentAPI;
import org.larrieulacoste.noe.al.trademe.domain.entity.User;
import org.larrieulacoste.noe.al.trademe.domain.event.Subscriber;
import org.larrieulacoste.noe.al.trademe.domain.event.UserApplicationEvent;
import org.larrieulacoste.noe.al.trademe.domain.exception.PaymentException;
import org.larrieulacoste.noe.al.trademe.domain.logger.Logger;
import org.larrieulacoste.noe.al.trademe.domain.logger.LoggerFactory;

import java.util.Objects;

public class PaymentService implements Subscriber<UserApplicationEvent> {
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
    public void accept(UserApplicationEvent userApplicationEvent) {
        this.processPayment(
                userApplicationEvent.getItem(),
                userApplicationEvent.getAmount()
        );
    }

}
