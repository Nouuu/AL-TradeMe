package org.larrieulacoste.noe.al.trademe.domain.model;

import org.larrieulacoste.noe.al.trademe.application.exception.InvalidEmailException;
import org.larrieulacoste.noe.al.trademe.domain.validators.StringValidators;

public final class EmailAddress {
    private final String emailAddressString;

    private EmailAddress(String emailAddressString) {
        if (!StringValidators.isEmail(emailAddressString)) {
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
