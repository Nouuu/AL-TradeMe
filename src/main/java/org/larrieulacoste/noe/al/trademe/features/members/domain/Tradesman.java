package org.larrieulacoste.noe.al.trademe.features.members.domain;

import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.domain.model.PaymentMethod;
import org.larrieulacoste.noe.al.trademe.domain.model.Project;
import org.larrieulacoste.noe.al.trademe.domain.model.TradesmanProfessionalAbilities;

import java.util.List;
import java.util.Objects;

public record Tradesman(
        EntityId entityId,
        NotEmptyString lastname,
        NotEmptyString firstname,
        EmailAddress email,
        Password password,
        SubscriptionStatus subscriptionStatus,
        PaymentMethod paymentMethod,
        TradesmanProfessionalAbilities professionalAbilities,
        List<Project> projects
) {
    public Tradesman {
        Objects.requireNonNull(entityId);
        Objects.requireNonNull(lastname);
        Objects.requireNonNull(firstname);
        Objects.requireNonNull(email);
        Objects.requireNonNull(password);
        Objects.requireNonNull(subscriptionStatus);
        Objects.requireNonNull(paymentMethod);
        Objects.requireNonNull(professionalAbilities);
        Objects.requireNonNull(projects);
    }

    public static Tradesman of(EntityId entityId, NotEmptyString lastname, NotEmptyString firstname, EmailAddress email,
            Password password, SubscriptionStatus subscriptionStatus, PaymentMethod paymentMethod,
            TradesmanProfessionalAbilities professionalAbilities, List<Project> projects) {
        return new Tradesman(entityId, lastname, firstname, email, password, subscriptionStatus, paymentMethod, professionalAbilities, projects);
    }

}
