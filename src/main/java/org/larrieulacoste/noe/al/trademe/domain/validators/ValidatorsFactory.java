package org.larrieulacoste.noe.al.trademe.domain.validators;

import org.larrieulacoste.noe.al.trademe.infrastructure.validators.SimpleDateValidators;
import org.larrieulacoste.noe.al.trademe.infrastructure.validators.SimpleStringValidators;

public final class ValidatorsFactory {
    private static final DateValidators DATE_VALIDATORS_INSTANCE = new SimpleDateValidators();
    private static final StringValidators STRING_VALIDATORS_INSTANCE = new SimpleStringValidators();

    private ValidatorsFactory() {
    }

    public static DateValidators getDateValidatorsInstance() {
        return DATE_VALIDATORS_INSTANCE;
    }

    public static StringValidators getStringValidatorsInstance() {
        return STRING_VALIDATORS_INSTANCE;
    }
}
