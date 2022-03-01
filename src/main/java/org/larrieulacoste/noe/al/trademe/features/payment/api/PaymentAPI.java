package org.larrieulacoste.noe.al.trademe.features.payment.api;

import org.larrieulacoste.noe.al.trademe.kernel.exception.PaymentException;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.PaymentMethod;

public interface PaymentAPI {
    void pay(PaymentMethod paymentMethod, double amount) throws PaymentException;
}
