package org.larrieulacoste.noe.al.trademe.features.payment.api;

public interface PaymentAPI {
    Boolean pay(String bankAccount, double amount);
}
