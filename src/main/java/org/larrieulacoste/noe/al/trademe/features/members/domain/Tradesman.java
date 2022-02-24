package org.larrieulacoste.noe.al.trademe.features.members.domain;

import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.domain.model.Location;
import org.larrieulacoste.noe.al.trademe.domain.model.PaymentMethod;
import org.larrieulacoste.noe.al.trademe.domain.model.TradesmanProfessionalAbilities;

import java.util.Objects;

public record Tradesman(
        EntityId entityId,
        NotEmptyString lastname,
        NotEmptyString firstname,
        EmailAddress email,
        Password password,
        Location address,
        SubscriptionStatus subscriptionStatus,
        PaymentMethod paymentMethod,
        TradesmanProfessionalAbilities professionalAbilities) {
    public Tradesman {
        Objects.requireNonNull(entityId);
        Objects.requireNonNull(lastname);
        Objects.requireNonNull(firstname);
        Objects.requireNonNull(email);
        Objects.requireNonNull(password);
        Objects.requireNonNull(address);
        Objects.requireNonNull(subscriptionStatus);
        Objects.requireNonNull(paymentMethod);
        Objects.requireNonNull(professionalAbilities);
    }

    public static Tradesman of(EntityId entityId, NotEmptyString lastname, NotEmptyString firstname, EmailAddress email,
            Password password, Location address, SubscriptionStatus subscriptionStatus, PaymentMethod paymentMethod,
            TradesmanProfessionalAbilities professionalAbilities) {
        return new Tradesman(entityId, lastname, firstname, email, password, address, subscriptionStatus, paymentMethod,
                professionalAbilities);
    }

}
