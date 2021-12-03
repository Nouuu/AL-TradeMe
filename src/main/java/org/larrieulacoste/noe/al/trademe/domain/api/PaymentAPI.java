package org.larrieulacoste.noe.al.trademe.domain.api;

public interface PaymentAPI {
    Boolean pay(String bankAccount, Double amount);
}
