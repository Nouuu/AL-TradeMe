package org.larrieulacoste.noe.al.trademe.features.payment.application;

import org.larrieulacoste.noe.al.trademe.application.event.NewTradesmanApplicative;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

public class NewTradesmanApplicationListener implements EventSubscriber<NewTradesmanApplicative> {
    private final PaymentService paymentService;

    public NewTradesmanApplicationListener(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public void accept(NewTradesmanApplicative event) {
        paymentService.processPayment(event.getTradesman(), event.getAmount());
    }
}
