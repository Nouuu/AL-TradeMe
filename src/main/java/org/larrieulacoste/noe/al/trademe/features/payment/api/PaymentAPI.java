package org.larrieulacoste.noe.al.trademe.features.payment.api;

import org.larrieulacoste.noe.al.trademe.application.exception.PaymentException;

public interface PaymentAPI {
    void pay(String bankAccount, double amount) throws PaymentException;
}
