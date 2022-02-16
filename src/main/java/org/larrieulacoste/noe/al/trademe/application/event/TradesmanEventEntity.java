package org.larrieulacoste.noe.al.trademe.application.event;

import java.util.List;

import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.domain.model.PaymentMethod;
import org.larrieulacoste.noe.al.trademe.domain.model.TradesmanProfessionalAbilities;
import org.larrieulacoste.noe.al.trademe.domain.model.Project;

public record TradesmanEventEntity(
        EntityId entityId,
        String firstname,
        String lastname, String email,
        String password,
        PaymentMethod paymentMethod,
        TradesmanProfessionalAbilities professionalAblilites, List<Project> projects) {

    public static TradesmanEventEntity of(EntityId entityId, String firstname, String lastname, String email,
            String password, PaymentMethod paymentMethod, TradesmanProfessionalAbilities professionalAblilites,
            List<Project> projects) {
        return new TradesmanEventEntity(entityId, firstname, lastname, email, password, paymentMethod,
                professionalAblilites, projects);
    }

    public static TradesmanEventEntity withoutPassword(EntityId entityId, String firstname, String lastname,
            String email, PaymentMethod paymentMethod, TradesmanProfessionalAbilities professionalAblilites,
            List<Project> projects) {
        return new TradesmanEventEntity(entityId, firstname, lastname, email, null, paymentMethod,
                professionalAblilites, projects);
    }

    public static TradesmanEventEntity withEntityIdOnly(EntityId entityId) {
        return new TradesmanEventEntity(entityId, null, null, null, null, null, null, null);
    }

    public static TradesmanEventEntity withEntityIdAndPaymentMethodOnly(EntityId entityId,
            PaymentMethod paymentMethod) {
        return new TradesmanEventEntity(entityId, null, null, null, null, paymentMethod, null, null);
    }
}
