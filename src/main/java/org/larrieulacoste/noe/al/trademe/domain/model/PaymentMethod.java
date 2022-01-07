package org.larrieulacoste.noe.al.trademe.domain.model;

import java.util.Objects;

public final class PaymentMethod {
    public final PaymentMethodType paymentMethodType;
    public final String paymentInfo;

    private PaymentMethod(PaymentMethodType paymentMethodType, String paymentInfo) {
        this.paymentMethodType = Objects.requireNonNull(paymentMethodType);
        this.paymentInfo = Objects.requireNonNull(paymentInfo);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentMethod that = (PaymentMethod) o;

        if (paymentMethodType != that.paymentMethodType) return false;
        return paymentInfo.equals(that.paymentInfo);
    }

    @Override
    public int hashCode() {
        int result = paymentMethodType.hashCode();
        result = 31 * result + paymentInfo.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PaymentMethod{" +
                "paymentMethodType=" + paymentMethodType +
                ", paymentInfo='" + paymentInfo + '\'' +
                '}';
    }
}
