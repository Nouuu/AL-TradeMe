package org.larrieulacoste.noe.al.trademe.configuration;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.larrieulacoste.noe.al.trademe.domain.model.Amount;
import org.larrieulacoste.noe.al.trademe.features.payment.domain.MembersSubscriptionAmount;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GlobalConfiguration {

    @ConfigProperty(name = "contractor.payment.monthly.amount")
    double contractorMonthlySubscriptionAmount;
    @ConfigProperty(name = "tradesman.payment.monthly.amount")
    double tradesmanMonthlySubscriptionAmount;

    @ApplicationScoped
    MembersSubscriptionAmount membersSubscriptionAmount() {
        return MembersSubscriptionAmount.of(
                Amount.of(contractorMonthlySubscriptionAmount),
                Amount.of(tradesmanMonthlySubscriptionAmount)
        );
    }

}
