package org.larrieulacoste.noe.al.trademe.features.payment.infrastructure;

import org.larrieulacoste.noe.al.trademe.application.exception.PaymentException;
import org.larrieulacoste.noe.al.trademe.features.payment.api.PaymentAPI;

public final class StubPaymentApi implements PaymentAPI {
    @Override
    public void pay(String bankAccount, double amount) throws PaymentException {
        // Stub normal payment
    }
}
