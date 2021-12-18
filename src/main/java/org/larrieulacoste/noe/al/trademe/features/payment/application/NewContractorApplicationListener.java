package org.larrieulacoste.noe.al.trademe.features.payment.application;

import org.larrieulacoste.noe.al.trademe.application.event.NewContractorApplicative;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

public class NewContractorApplicationListener implements EventSubscriber<NewContractorApplicative> {

    private final PaymentService paymentService;

    public NewContractorApplicationListener(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public void accept(NewContractorApplicative event) {
        paymentService.processPayment(event.getContractor(), event.getAmount());
    }
}
