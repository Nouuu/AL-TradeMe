package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.domain.model.PaymentMethod;

public record ContractorEventEntity(EntityId entityId,
        String firstname, String lastname, String email,
        String password,
        PaymentMethod paymentMethod) {

    public static ContractorEventEntity of(EntityId entityId, String firstname, String lastname, String email,
            String password, PaymentMethod paymentMethod) {
        return new ContractorEventEntity(entityId, firstname, lastname, email, password, paymentMethod);
    }

    public static ContractorEventEntity withoutPassword(EntityId entityId, String firstname, String lastname,
            String email, PaymentMethod paymentMethod) {
        return new ContractorEventEntity(entityId, firstname, lastname, email, null, paymentMethod);
    }

    public static ContractorEventEntity withEntityIdOnly(EntityId entityId) {
        return new ContractorEventEntity(entityId, null, null, null, null, null);
    }

    public static ContractorEventEntity withEntityIdAndPaymentMethodOnly(EntityId entityId,
            PaymentMethod paymentMethod) {
        return new ContractorEventEntity(entityId, null, null, null, null, paymentMethod);
    }
}
