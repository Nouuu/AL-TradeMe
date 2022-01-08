package org.larrieulacoste.noe.al.trademe.features.payment.api;

import org.larrieulacoste.noe.al.trademe.application.exception.PaymentException;
import org.larrieulacoste.noe.al.trademe.domain.model.PaymentMethod;

public interface PaymentAPI {
    void pay(PaymentMethod paymentMethod, double amount) throws PaymentException;
}
