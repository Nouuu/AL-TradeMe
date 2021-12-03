package org.larrieulacoste.noe.al.domain.api;

public interface PaymentAPI {
    Boolean pay(String bankAccount, Double amount);
}
