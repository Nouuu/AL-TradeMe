package org.larrieulacoste.noe.al.trademe.domain.validators;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class StringValidators {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    private static final String PASSWORD_REGEX = "^.{8}.*$";

    private StringValidators() {
    }

    public static boolean isNotEmptyOrOnlyWhitespaces(String item) {
        return isNotBlank(item);
    }

    public static boolean isValidPassword(String item) {
        return item != null && item.matches(PASSWORD_REGEX);
    }

    public static boolean isEmail(String email) {
        return isNotBlank(email) && email.matches(EMAIL_REGEX);
    }
}
