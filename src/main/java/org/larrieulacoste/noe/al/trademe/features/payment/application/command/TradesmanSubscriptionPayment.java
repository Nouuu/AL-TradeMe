package org.larrieulacoste.noe.al.trademe.features.payment.application.command;

import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.kernel.command.Command;

import java.util.Objects;

public final class TradesmanSubscriptionPayment implements Command {
    public final EntityId tradesmanId;
    public final String paymentMethod;

    public TradesmanSubscriptionPayment(EntityId tradesmanId, String paymentMethod) {
        this.tradesmanId = tradesmanId;
        this.paymentMethod = paymentMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TradesmanSubscriptionPayment that = (TradesmanSubscriptionPayment) o;
        return Objects.equals(tradesmanId, that.tradesmanId) && Objects.equals(paymentMethod, that.paymentMethod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tradesmanId, paymentMethod);
    }

    @Override
    public String toString() {
        return "TradesmanPayment{" +
                "tradesmanId=" + tradesmanId +
                ", paymentMethod='" + paymentMethod + '\'' +
                '}';
    }
}
