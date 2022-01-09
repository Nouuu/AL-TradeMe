package org.larrieulacoste.noe.al.trademe.kernel.validators;

public interface PaymentInformationsValidator {
    boolean isValidPaymentMethod(String paymentMethodType, String paymentMethodRessource);
}
