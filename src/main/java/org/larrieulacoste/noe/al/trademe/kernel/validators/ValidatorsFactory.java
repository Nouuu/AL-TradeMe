package org.larrieulacoste.noe.al.trademe.kernel.validators;

public final class ValidatorsFactory {
    private static final DateValidators DATE_VALIDATORS_INSTANCE = new SimpleDateValidators();
    private static final StringValidators STRING_VALIDATORS_INSTANCE = new SimpleStringValidators();
    private static final PaymentInformationsValidator PAYMENT_INFORMATIONS_VALIDATOR = new SimplePaymentInformationsValidator();

    private ValidatorsFactory() {
    }

    public static DateValidators getDateValidatorsInstance() {
        return DATE_VALIDATORS_INSTANCE;
    }

    public static StringValidators getStringValidatorsInstance() {
        return STRING_VALIDATORS_INSTANCE;
    }

    public static PaymentInformationsValidator getPaymentInformationsValidator() {
        return PAYMENT_INFORMATIONS_VALIDATOR;
    }
}
