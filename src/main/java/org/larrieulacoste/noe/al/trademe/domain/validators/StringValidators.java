package org.larrieulacoste.noe.al.trademe.domain.validators;

public interface StringValidators {
    boolean isNotEmptyOrOnlyWhitespaces(String item);

    boolean isValidPassword(String item);

    boolean isEmail(String email);
}
