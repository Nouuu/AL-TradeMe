package org.larrieulacoste.noe.al.trademe.features.members.application.command;

import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.features.members.domain.SubscriptionStatus;
import org.larrieulacoste.noe.al.trademe.kernel.command.Command;

public final class UpdateContractorSubscriptionStatus implements Command {
    public final EntityId contractorId;
    public final SubscriptionStatus subscriptionStatus;

    public UpdateContractorSubscriptionStatus(EntityId contractorId, SubscriptionStatus subscriptionStatus) {
        this.contractorId = contractorId;
        this.subscriptionStatus = subscriptionStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UpdateContractorSubscriptionStatus that = (UpdateContractorSubscriptionStatus) o;

        if (!contractorId.equals(that.contractorId)) return false;
        return subscriptionStatus == that.subscriptionStatus;
    }

    @Override
    public int hashCode() {
        int result = contractorId.hashCode();
        result = 31 * result + subscriptionStatus.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "UpdateContractorSubscriptionStatus{" +
                "contractorId=" + contractorId +
                ", subscriptionStatus=" + subscriptionStatus +
                '}';
    }
}
