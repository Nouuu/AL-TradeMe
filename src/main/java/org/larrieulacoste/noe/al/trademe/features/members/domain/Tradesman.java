package org.larrieulacoste.noe.al.trademe.features.members.domain;

import org.larrieulacoste.noe.al.trademe.domain.model.EmailAddress;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.domain.model.NotEmptyString;
import org.larrieulacoste.noe.al.trademe.domain.model.Password;

import java.util.Objects;

public final class Tradesman {
    private final EntityId entityId;
    private final NotEmptyString lastname;
    private final NotEmptyString firstname;
    private final EmailAddress email;
    private final Password password;

    private Tradesman(EntityId entityId, NotEmptyString lastname, NotEmptyString firstname, EmailAddress email, Password password) {
        this.entityId = Objects.requireNonNull(entityId);
        this.lastname = Objects.requireNonNull(lastname);
        this.firstname = Objects.requireNonNull(firstname);
        this.email = Objects.requireNonNull(email);
        this.password = Objects.requireNonNull(password);
    }

    public static Tradesman of(EntityId entityId, NotEmptyString lastname, NotEmptyString firstname, EmailAddress email, Password password) {
        return new Tradesman(entityId, lastname, firstname, email, password);
    }

    public EntityId getEntityId() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tradesman tradesman = (Tradesman) o;

        if (!entityId.equals(tradesman.entityId)) return false;
        if (!lastname.equals(tradesman.lastname)) return false;
        if (!firstname.equals(tradesman.firstname)) return false;
        if (!email.equals(tradesman.email)) return false;
        return password.equals(tradesman.password);
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

    @Override
    public String toString() {
        return "Tradesman{" +
                "entityId=" + entityId +
                ", lastname=" + lastname +
                ", firstname=" + firstname +
                ", email=" + email +
                ", password=" + password +
                '}';
    }
}
