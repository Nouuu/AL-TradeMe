package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.domain.model.Location;
import org.larrieulacoste.noe.al.trademe.domain.model.PaymentMethod;
import org.larrieulacoste.noe.al.trademe.domain.model.TradesmanProfessionalAbilites;

public record TradesmanEventEntity(
        EntityId entityId,
        String firstname,
        String lastname,
        String email,
        String password,
        PaymentMethod paymentMethod,
        Location address,
        TradesmanProfessionalAbilites professionalAblilites) {

    public static TradesmanEventEntity of(EntityId entityId, String firstname, String lastname, String email,
            String password, PaymentMethod paymentMethod, Location address,
            TradesmanProfessionalAbilites professionalAblilites) {
        return new TradesmanEventEntity(entityId, firstname, lastname, email, password, paymentMethod, address,
                professionalAblilites);
    }

    public static TradesmanEventEntity withoutPassword(EntityId entityId, String firstname, String lastname,
            String email, PaymentMethod paymentMethod, Location address,
            TradesmanProfessionalAbilites professionalAblilites) {
        return new TradesmanEventEntity(entityId, firstname, lastname, email, null, paymentMethod, address,
                professionalAblilites);
    }

    public static TradesmanEventEntity withEntityIdOnly(EntityId entityId) {
        return new TradesmanEventEntity(entityId, null, null, null, null, null, null, null);
    }

    public static TradesmanEventEntity withEntityIdAndPaymentMethodOnly(EntityId entityId,
            PaymentMethod paymentMethod) {
        return new TradesmanEventEntity(entityId, null, null, null, null, paymentMethod, null, null);
    }
}
