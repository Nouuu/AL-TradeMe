package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.domain.model.PaymentMethod;

import java.util.Objects;

public final class TradesmanEventEntity {
    public final EntityId entityId;
    public final String firstname;
    public final String lastname;
    public final String email;
    public final String password;
    public final PaymentMethod paymentMethod;

    private TradesmanEventEntity(EntityId entityId, String firstname, String lastname, String email, String password, PaymentMethod paymentMethod) {
        this.entityId = entityId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.paymentMethod = paymentMethod;
    }

    public static TradesmanEventEntity of(EntityId entityId, String firstname, String lastname, String email, String password, PaymentMethod paymentMethod) {
        return new TradesmanEventEntity(entityId, firstname, lastname, email, password, paymentMethod);
    }

    public static TradesmanEventEntity withoutPassword(EntityId entityId, String firstname, String lastname, String email, PaymentMethod paymentMethod) {
        return new TradesmanEventEntity(entityId, firstname, lastname, email, null, paymentMethod);
    }

    public static TradesmanEventEntity withEntityIdOnly(EntityId entityId) {
        return new TradesmanEventEntity(entityId, null, null, null, null, null);
    }

    public static TradesmanEventEntity withEntityIdAndPaymentMethodOnly(EntityId entityId, PaymentMethod paymentMethod) {
        return new TradesmanEventEntity(entityId, null, null, null, null, paymentMethod);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TradesmanEventEntity that = (TradesmanEventEntity) o;

        if (!Objects.equals(entityId, that.entityId)) return false;
        if (!Objects.equals(firstname, that.firstname)) return false;
        if (!Objects.equals(lastname, that.lastname)) return false;
        if (!Objects.equals(email, that.email)) return false;
        return Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        int result = entityId != null ? entityId.hashCode() : 0;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TradesmanEventEntity{" +
                "entityId=" + entityId +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
