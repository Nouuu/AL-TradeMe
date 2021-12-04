package org.larrieulacoste.noe.al.trademe.domain.model;

import org.larrieulacoste.noe.al.trademe.application.exception.InvalidEmailException;

import java.util.Objects;

public final class EmailAddress {
    private final String emailAddressString;
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    private EmailAddress(String emailAddressString) {
        if (!Objects.requireNonNull(emailAddressString).matches(EMAIL_REGEX)) {
            throw new InvalidEmailException("Invalid email : " + emailAddressString);
        }
        this.emailAddressString = emailAddressString;
    }

    public static EmailAddress of(String emailAddress) {
        return new EmailAddress(emailAddress);
    }

    public String getEmailAddressString() {
        return emailAddressString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmailAddress)) return false;

        EmailAddress that = (EmailAddress) o;

        return emailAddressString.equals(that.emailAddressString);
    }

    @Override
    public int hashCode() {
        return emailAddressString.hashCode();
    }

    @Override
    public String toString() {
        return "EmailAddress{" +
                "emailAddress='" + emailAddressString + '\'' +
                '}';
    }
}
