package org.larrieulacoste.noe.al.trademe.features.payment.domain;

import org.larrieulacoste.noe.al.trademe.shared_kernel.model.Amount;

public record MembersSubscriptionAmount(
        Amount contractorSubscriptionAmount,
        Amount tradesmanSubscriptionAmount
) {

    public static MembersSubscriptionAmount of(Amount contractorSubscriptionAmount, Amount tradesmanSubscriptionAmount) {
        return new MembersSubscriptionAmount(contractorSubscriptionAmount, tradesmanSubscriptionAmount);
    }
}
