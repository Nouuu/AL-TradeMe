package org.larrieulacoste.noe.al.infrastructure.api;

import org.larrieulacoste.noe.al.domain.api.PaymentAPI;

public class StubPaymentApi implements PaymentAPI {
    @Override
    public Boolean pay(String bankAccount, Double amount) {
        return true;
    }
}
