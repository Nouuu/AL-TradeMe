package org.larrieulacoste.noe.al.trademe.features.members.domain;

import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.domain.model.PaymentMethod;

import java.util.Objects;

public record Contractor(
        EntityId entityId,
        NotEmptyString lastname,
        NotEmptyString firstname,
        EmailAddress email,
        Password password,
        SubscriptionStatus subscriptionStatus,
        PaymentMethod paymentMethod) {

    public Contractor {
        Objects.requireNonNull(entityId);
        Objects.requireNonNull(lastname);
        Objects.requireNonNull(firstname);
        Objects.requireNonNull(email);
        Objects.requireNonNull(password);
        Objects.requireNonNull(subscriptionStatus);
        Objects.requireNonNull(paymentMethod);
    }

    public static Contractor of(EntityId entityId, NotEmptyString lastname, NotEmptyString firstname,
            EmailAddress email, Password password, SubscriptionStatus subscriptionStatus, PaymentMethod paymentMethod) {
        return new Contractor(entityId, lastname, firstname, email, password, subscriptionStatus, paymentMethod);
    }

}
