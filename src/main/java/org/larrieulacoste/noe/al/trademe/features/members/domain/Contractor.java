package org.larrieulacoste.noe.al.trademe.features.members.domain;

import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.domain.model.PaymentMethod;

import java.util.Objects;

public final class Contractor {

    public final EntityId entityId;
    public final NotEmptyString lastname;
    public final NotEmptyString firstname;
    public final EmailAddress email;
    public final Password password;
    public final SubscriptionStatus subscriptionStatus;
    public final PaymentMethod paymentMethod;

    private Contractor(EntityId entityId, NotEmptyString lastname, NotEmptyString firstname, EmailAddress email, Password password, SubscriptionStatus subscriptionStatus, PaymentMethod paymentMethod) {
        this.entityId = Objects.requireNonNull(entityId);
        this.lastname = Objects.requireNonNull(lastname);
        this.firstname = Objects.requireNonNull(firstname);
        this.email = Objects.requireNonNull(email);
        this.password = Objects.requireNonNull(password);
        this.subscriptionStatus = Objects.requireNonNull(subscriptionStatus);
        this.paymentMethod = Objects.requireNonNull(paymentMethod);
    }

    public static Contractor of(EntityId entityId, NotEmptyString lastname, NotEmptyString firstname, EmailAddress email, Password password, SubscriptionStatus subscriptionStatus, PaymentMethod paymentMethod) {
        return new Contractor(entityId, lastname, firstname, email, password, subscriptionStatus, paymentMethod);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contractor that = (Contractor) o;

        if (!entityId.equals(that.entityId)) return false;
        if (!lastname.equals(that.lastname)) return false;
        if (!firstname.equals(that.firstname)) return false;
        if (!email.equals(that.email)) return false;
        if (!password.equals(that.password)) return false;
        if (subscriptionStatus != that.subscriptionStatus) return false;
        return paymentMethod.equals(that.paymentMethod);
    }

    @Override
    public int hashCode() {
        int result = entityId.hashCode();
        result = 31 * result + lastname.hashCode();
        result = 31 * result + firstname.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + subscriptionStatus.hashCode();
        result = 31 * result + paymentMethod.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Contractor{" +
                "entityId=" + entityId +
                ", lastname=" + lastname +
                ", firstname=" + firstname +
                ", email=" + email +
                ", subscriptionStatus=" + subscriptionStatus +
                ", paymentMethod=" + paymentMethod +
                '}';
    }
}
