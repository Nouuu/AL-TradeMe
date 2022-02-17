package org.larrieulacoste.noe.al.trademe.features.members.domain;

import org.larrieulacoste.noe.al.trademe.kernel.validators.StringValidators;

import java.util.Objects;

public record NotEmptyString(String value) {
    public NotEmptyString {
        Objects.requireNonNull(value);
    }

    private NotEmptyString(String value, StringValidators stringValidators) {
        this(value);
        if (!stringValidators.isNotEmptyOrOnlyWhitespaces(value)) {
            throw new IllegalArgumentException("Field must not be empty");
        }
    }

    public static NotEmptyString of(String value, StringValidators stringValidators) {
        return new NotEmptyString(value, stringValidators);
    }
}
