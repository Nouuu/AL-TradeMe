package org.larrieulacoste.noe.al.trademe.infrastructure.api;

import org.larrieulacoste.noe.al.trademe.domain.api.PaymentAPI;

public final class StubPaymentApi implements PaymentAPI {
    @Override
    public Boolean pay(String bankAccount, Double amount) {
        return true;
    }
}
