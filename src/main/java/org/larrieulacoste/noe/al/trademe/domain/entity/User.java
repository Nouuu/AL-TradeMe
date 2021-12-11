package org.larrieulacoste.noe.al.trademe.domain.entity;

import org.larrieulacoste.noe.al.trademe.domain.model.EmailAddress;
import org.larrieulacoste.noe.al.trademe.domain.model.NotEmptyString;
import org.larrieulacoste.noe.al.trademe.domain.model.Password;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;

import java.util.Objects;

public class User {

    private final EntityId entityId;
    private NotEmptyString lastname;
    private NotEmptyString firstname;
    private EmailAddress email;
    private Password password;

    protected User(EntityId entityId, NotEmptyString lastname, NotEmptyString firstname, EmailAddress email, Password password) {
        this.entityId = Objects.requireNonNull(entityId);
        this.lastname = Objects.requireNonNull(lastname);
        this.firstname = Objects.requireNonNull(firstname);
        this.email = Objects.requireNonNull(email);
        this.password = Objects.requireNonNull(password);
    }

    public static User of(EntityId entityId, NotEmptyString lastname, NotEmptyString firstname, EmailAddress email, Password password) {
        return new User(entityId, lastname, firstname, email, password);
    }

    public void updateLastname(NotEmptyString lastname) {
        this.lastname = lastname;
    }

    public void updateFirstname(NotEmptyString firstname) {
        this.firstname = firstname;
    }

    public void updateEmail(EmailAddress email) {
        this.email = email;
    }

    public void updatePassword(Password password) {
        this.password = password;
    }

    public EntityId getUserId() {
        return entityId;
    }

    public NotEmptyString getLastname() {
        return lastname;
    }

    public NotEmptyString getFirstname() {
        return firstname;
    }

    public EmailAddress getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + entityId +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (!entityId.equals(user.entityId)) return false;
        if (!lastname.equals(user.lastname)) return false;
        if (!firstname.equals(user.firstname)) return false;
        if (!email.equals(user.email)) return false;
        return password.equals(user.password);
    }

    @Override
    public int hashCode() {
        int result = entityId.hashCode();
        result = 31 * result + lastname.hashCode();
        result = 31 * result + firstname.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
}
