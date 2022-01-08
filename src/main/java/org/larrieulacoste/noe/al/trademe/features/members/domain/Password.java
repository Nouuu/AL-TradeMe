package org.larrieulacoste.noe.al.trademe.features.members.domain;

import org.larrieulacoste.noe.al.trademe.application.exception.InvalidPasswordException;
import org.larrieulacoste.noe.al.trademe.domain.validators.ValidatorsFactory;

public final class Password {
    public final String value;

    private Password(String value) {
        if (!ValidatorsFactory.getStringValidatorsInstance().isValidPassword(value)) {
            throw new InvalidPasswordException("Invalid password : " + value);
        }
        this.value = value;
    }

    public static Password of(String password) {
        return new Password(password);
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
