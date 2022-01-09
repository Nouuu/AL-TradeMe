package org.larrieulacoste.noe.al.trademe.features.payment.domain;

import org.larrieulacoste.noe.al.trademe.domain.model.Amount;

public final class MembersSubscriptionAmount {
    public final Amount contractorSubscriptionAmount;
    public final Amount tradesmanSubscriptionAmount;

    private MembersSubscriptionAmount(Amount contractorSubscriptionAmount, Amount tradesmanSubscriptionAmount) {
        this.contractorSubscriptionAmount = contractorSubscriptionAmount;
        this.tradesmanSubscriptionAmount = tradesmanSubscriptionAmount;
    }

    public static MembersSubscriptionAmount of(Amount contractorSubscriptionAmount, Amount tradesmanSubscriptionAmount) {
        return new MembersSubscriptionAmount(contractorSubscriptionAmount, tradesmanSubscriptionAmount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MembersSubscriptionAmount that = (MembersSubscriptionAmount) o;

        if (!contractorSubscriptionAmount.equals(that.contractorSubscriptionAmount)) return false;
        return tradesmanSubscriptionAmount.equals(that.tradesmanSubscriptionAmount);
    }

    @Override
    public int hashCode() {
        int result = contractorSubscriptionAmount.hashCode();
        result = 31 * result + tradesmanSubscriptionAmount.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "MembersSubscriptionAmount{" +
                "contractorSubscriptionAmount=" + contractorSubscriptionAmount +
                ", tradesmanSubscriptionAmount=" + tradesmanSubscriptionAmount +
                '}';
    }
}
