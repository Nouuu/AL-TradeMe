package org.larrieulacoste.noe.al.trademe.domain.model;

import java.util.Arrays;

public enum PaymentMethodType {
    PAYPAL("PAYPAL"),
    BANK_ACCOUNT("BANK_ACCOUNT");

    public final String value;

    PaymentMethodType(String value) {
        this.value = value;
    }

    public static PaymentMethodType fromString(String value) {
        return Arrays.stream(PaymentMethodType.values())
                .filter(paymentMethodType -> paymentMethodType.value.equals(value))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return "PaymentMethodType{" +
                "value='" + value + '\'' +
                '}';
    }
}
