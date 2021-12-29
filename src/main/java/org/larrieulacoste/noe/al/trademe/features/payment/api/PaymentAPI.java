package org.larrieulacoste.noe.al.trademe.features.payment.api;

public interface PaymentAPI {
    void pay(String bankAccount, double amount);
}
