package org.larrieulacoste.noe.al.trademe.infrastructure.validators;

import org.larrieulacoste.noe.al.trademe.domain.validators.StringValidators;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public final class SimpleStringValidators implements StringValidators {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    private static final String PASSWORD_REGEX = "^.{8}.*$";

    @Override
    public boolean isNotEmptyOrOnlyWhitespaces(String item) {
        return isNotBlank(item);
    }

    @Override
    public boolean isValidPassword(String item) {
        return item != null && item.matches(PASSWORD_REGEX);
    }

    @Override
    public boolean isEmail(String email) {
        return email != null && email.matches(EMAIL_REGEX);
    }
}
