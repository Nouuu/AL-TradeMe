package org.larrieulacoste.noe.al.trademe.features.members.domain;

import org.larrieulacoste.noe.al.trademe.domain.exception.InvalidEmailException;
import org.larrieulacoste.noe.al.trademe.kernel.validators.StringValidators;

public final class EmailAddress {
    public final String value;

    private EmailAddress(String value, StringValidators stringValidators) {
        if (!stringValidators.isEmail(value)) {
            throw new InvalidEmailException("Invalid email : " + value);
        }
        this.value = value;
    }

    public static EmailAddress of(String emailAddress, StringValidators stringValidators) {
        return new EmailAddress(emailAddress, stringValidators);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmailAddress)) return false;

        EmailAddress that = (EmailAddress) o;

        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return "EmailAddress{" +
                "value='" + value + '\'' +
                '}';
    }
}
