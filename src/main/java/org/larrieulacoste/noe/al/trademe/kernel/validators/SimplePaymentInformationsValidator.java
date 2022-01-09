package org.larrieulacoste.noe.al.trademe.kernel.validators;

import org.larrieulacoste.noe.al.trademe.domain.model.PaymentMethodType;
import org.larrieulacoste.noe.al.trademe.kernel.validators.PaymentInformationsValidator;

public final class SimplePaymentInformationsValidator implements PaymentInformationsValidator {

    @Override
    public boolean isValidPaymentMethod(String paymentMethodType, String paymentMethodRessource) {
        return PaymentMethodType.fromString(paymentMethodType) != null;
    }
}
