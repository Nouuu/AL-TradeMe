package org.larrieulacoste.noe.al.trademe.features.payment.infrastructure;

import org.larrieulacoste.noe.al.trademe.features.payment.api.PaymentAPI;
import org.larrieulacoste.noe.al.trademe.kernel.exception.PaymentException;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.PaymentMethod;

public final class StubPaymentApi implements PaymentAPI {
    @Override
    public void pay(PaymentMethod bankAccount, double amount) throws PaymentException {
        // Stub normal payment
    }
}
