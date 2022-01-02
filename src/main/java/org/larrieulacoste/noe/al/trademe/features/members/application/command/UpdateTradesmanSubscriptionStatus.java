package org.larrieulacoste.noe.al.trademe.features.members.application.command;

import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.features.members.domain.SubscriptionStatus;
import org.larrieulacoste.noe.al.trademe.kernel.command.Command;

public class UpdateTradesmanSubscriptionStatus implements Command {
    public final EntityId tradesmanId;
    public final SubscriptionStatus subscriptionStatus;

    public UpdateTradesmanSubscriptionStatus(EntityId tradesmanId, SubscriptionStatus subscriptionStatus) {
        this.tradesmanId = tradesmanId;
        this.subscriptionStatus = subscriptionStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UpdateTradesmanSubscriptionStatus that = (UpdateTradesmanSubscriptionStatus) o;

        if (!tradesmanId.equals(that.tradesmanId)) return false;
        return subscriptionStatus == that.subscriptionStatus;
    }

    @Override
    public int hashCode() {
        int result = tradesmanId.hashCode();
        result = 31 * result + subscriptionStatus.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "UpdateTradesmanSubscriptionStatus{" +
                "tradesmanId=" + tradesmanId +
                ", subscriptionStatus=" + subscriptionStatus +
                '}';
    }
}
