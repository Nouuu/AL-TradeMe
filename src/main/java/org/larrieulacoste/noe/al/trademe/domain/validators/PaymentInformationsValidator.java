package org.larrieulacoste.noe.al.trademe.domain.validators;

public interface PaymentInformationsValidator {
    boolean isValidPaymentMethod(String paymentMethodType, String paymentMethodRessource);
}
