package org.larrieulacoste.noe.al.trademe.features.members.domain;

import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.domain.model.PaymentMethod;

import java.util.Objects;

public record Tradesman(
        EntityId entityId,
        NotEmptyString lastname,
        NotEmptyString firstname,
        EmailAddress email,
        Password password,
        SubscriptionStatus subscriptionStatus,
        PaymentMethod paymentMethod
) {
    public Tradesman {
        Objects.requireNonNull(entityId);
        Objects.requireNonNull(lastname);
        Objects.requireNonNull(firstname);
        Objects.requireNonNull(email);
        Objects.requireNonNull(password);
        Objects.requireNonNull(subscriptionStatus);
        Objects.requireNonNull(paymentMethod);
    }

    public static Tradesman of(EntityId entityId, NotEmptyString lastname, NotEmptyString firstname, EmailAddress email, Password password, SubscriptionStatus subscriptionStatus, PaymentMethod paymentMethod) {
        return new Tradesman(entityId, lastname, firstname, email, password, subscriptionStatus, paymentMethod);
    }

}
