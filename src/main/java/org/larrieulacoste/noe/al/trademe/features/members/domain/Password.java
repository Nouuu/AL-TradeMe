package org.larrieulacoste.noe.al.trademe.features.members.domain;

import org.larrieulacoste.noe.al.trademe.kernel.exception.InvalidPasswordException;
import org.larrieulacoste.noe.al.trademe.kernel.validators.StringValidators;

import java.util.Objects;

public record Password(String value) {
    public Password {
        Objects.requireNonNull(value);
    }

    private Password(String value, StringValidators stringValidators) {
        this(value);
        if (!stringValidators.isValidPassword(value)) {
            throw new InvalidPasswordException("Invalid password : " + value);
        }
    }

    public static Password of(String password, StringValidators stringValidators) {
        return new Password(password, stringValidators);
    }

    @Override
    public String toString() {
        return "Password{" +
                "passwordString='" + value + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Password)) return false;

        Password password = (Password) o;

        return value.equals(password.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
