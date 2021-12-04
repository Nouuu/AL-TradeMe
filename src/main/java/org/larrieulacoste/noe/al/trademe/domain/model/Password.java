package org.larrieulacoste.noe.al.trademe.domain.model;

import org.apache.commons.lang3.StringUtils;
import org.larrieulacoste.noe.al.trademe.domain.exception.InvalidPasswordException;

public class Password {
    private final String passwordString;

    private Password(String passwordString) {
        if (StringUtils.isBlank(passwordString) || passwordString.length() < 8) {
            throw new InvalidPasswordException("Invalid password : " + passwordString);
        }
        this.passwordString = passwordString;
    }

    public static Password of(String password) {
        return new Password(password);
    }

    public String getPasswordString() {
        return passwordString;
    }

    @Override
    public String toString() {
        return "Password{" +
                "passwordString='" + passwordString + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Password)) return false;

        Password password = (Password) o;

        return passwordString.equals(password.passwordString);
    }

    @Override
    public int hashCode() {
        return passwordString.hashCode();
    }
}
