package org.larrieulacoste.noe.al.trademe.features.payment.application;

import org.larrieulacoste.noe.al.trademe.application.event.NewContractorApplicative;
import org.larrieulacoste.noe.al.trademe.application.event.NewTradesmanApplicative;
import org.larrieulacoste.noe.al.trademe.application.exception.PaymentException;
import org.larrieulacoste.noe.al.trademe.domain.entity.User;
import org.larrieulacoste.noe.al.trademe.domain.logger.Logger;
import org.larrieulacoste.noe.al.trademe.domain.logger.LoggerFactory;
import org.larrieulacoste.noe.al.trademe.features.payment.api.PaymentAPI;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

import java.util.Objects;

public final class PaymentService {
    private final Logger logger;
    private final PaymentAPI paymentAPI;
    private final NewContractorApplicationListener newContractorApplicativeListener;
    private final NewTradesmanApplicationListener newTradesmanApplicativeListener;

    public PaymentService(LoggerFactory loggerFactory, PaymentAPI paymentAPI) {
        this.logger = Objects.requireNonNull(loggerFactory).getLogger(this);
        this.paymentAPI = Objects.requireNonNull(paymentAPI);
        this.newContractorApplicativeListener = new NewContractorApplicationListener();
        this.newTradesmanApplicativeListener = new NewTradesmanApplicationListener();
    }

    public void processPayment(User user, Double amount) throws PaymentException {
        logger.log(String.format("Process user payment of : %s with amount %.2f", user, amount));

        Boolean paymentSuccess = paymentAPI.pay(
                "TODO",
                Double.max(Objects.requireNonNull(amount), 0)
        );

        if (Boolean.TRUE.equals(paymentSuccess)) {
            logger.log("Payment success !");
        } else {
            throw new PaymentException("Payment error for user : " + user);
        }
    }

    public NewContractorApplicationListener getNewContractorApplicativeListener() {
        return newContractorApplicativeListener;
    }

    public NewTradesmanApplicationListener getNewTradesmanApplicativeListener() {
        return newTradesmanApplicativeListener;
    }

    private class NewTradesmanApplicationListener implements EventSubscriber<NewTradesmanApplicative> {
        private NewTradesmanApplicationListener() {
        }

        @Override
        public void accept(NewTradesmanApplicative event) {
            processPayment(event.getTradesman(), event.getAmount());
        }
    }

    private class NewContractorApplicationListener implements EventSubscriber<NewContractorApplicative> {
        private NewContractorApplicationListener() {
        }

        @Override
        public void accept(NewContractorApplicative event) {
            processPayment(event.getContractor(), event.getAmount());
        }
    }
}
