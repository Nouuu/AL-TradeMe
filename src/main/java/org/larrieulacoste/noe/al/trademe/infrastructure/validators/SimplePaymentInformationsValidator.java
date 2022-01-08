package org.larrieulacoste.noe.al.trademe.infrastructure.validators;

import org.larrieulacoste.noe.al.trademe.domain.model.PaymentMethodType;
import org.larrieulacoste.noe.al.trademe.domain.validators.PaymentInformationsValidator;

public class SimplePaymentInformationsValidator implements PaymentInformationsValidator {

    @Override
    public boolean isValidPaymentMethod(String paymentMethodType, String paymentMethodRessource) {
        return PaymentMethodType.fromString(paymentMethodType) != null;
    }
}
