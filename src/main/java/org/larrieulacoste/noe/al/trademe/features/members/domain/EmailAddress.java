package org.larrieulacoste.noe.al.trademe.features.members.domain;

import org.larrieulacoste.noe.al.trademe.domain.exception.InvalidEmailException;
import org.larrieulacoste.noe.al.trademe.kernel.validators.StringValidators;

import java.util.Objects;

public record EmailAddress(String value) {

    public EmailAddress {
        Objects.requireNonNull(value);
    }

    private EmailAddress(String value, StringValidators stringValidators) {
        this(value);
        if (!stringValidators.isEmail(value)) {
            throw new InvalidEmailException("Invalid email : " + value);
        }
    }

    public static EmailAddress of(String emailAddress, StringValidators stringValidators) {
        return new EmailAddress(emailAddress, stringValidators);
    }
}
