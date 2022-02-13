package org.larrieulacoste.noe.al.trademe.domain.model;

import java.util.Objects;

public record PaymentMethod(PaymentMethodType paymentMethodType,
                            String paymentInfo) {
    public PaymentMethod {
        Objects.requireNonNull(paymentMethodType);
        Objects.requireNonNull(paymentInfo);
    }

    public static PaymentMethod withBankAccount(String bankAccount) {
        return new PaymentMethod(PaymentMethodType.BANK_ACCOUNT, bankAccount);
    }

    public static PaymentMethod withPaypal(String paypalEmail) {
        return new PaymentMethod(PaymentMethodType.PAYPAL, paypalEmail);
    }

    public static PaymentMethod of(String paymentMethod, String paymentInfo) {
        return new PaymentMethod(PaymentMethodType.fromString(paymentMethod), paymentInfo);
    }
}
