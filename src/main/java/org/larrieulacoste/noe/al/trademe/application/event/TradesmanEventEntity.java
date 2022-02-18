package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.domain.model.PaymentMethod;
import org.larrieulacoste.noe.al.trademe.domain.model.TradesmanProfessionalAbilities;

public record TradesmanEventEntity(
        EntityId entityId,
        String firstname,
        String lastname, String email,
        String password,
        PaymentMethod paymentMethod,
        TradesmanProfessionalAbilities professionalAblilites) {

    public static TradesmanEventEntity of(EntityId entityId, String firstname, String lastname, String email,
            String password, PaymentMethod paymentMethod, TradesmanProfessionalAbilities professionalAblilites) {
        return new TradesmanEventEntity(entityId, firstname, lastname, email, password, paymentMethod,
                professionalAblilites);
    }

    public static TradesmanEventEntity withoutPassword(EntityId entityId, String firstname, String lastname,
            String email, PaymentMethod paymentMethod, TradesmanProfessionalAbilities professionalAblilites) {
        return new TradesmanEventEntity(entityId, firstname, lastname, email, null, paymentMethod,
                professionalAblilites);
    }

    public static TradesmanEventEntity withEntityIdOnly(EntityId entityId) {
        return new TradesmanEventEntity(entityId, null, null, null, null, null, null);
    }

    public static TradesmanEventEntity withEntityIdAndPaymentMethodOnly(EntityId entityId,
            PaymentMethod paymentMethod) {
        return new TradesmanEventEntity(entityId, null, null, null, null, paymentMethod, null);
    }
}
