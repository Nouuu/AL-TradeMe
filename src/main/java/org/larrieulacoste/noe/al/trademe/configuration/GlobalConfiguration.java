package org.larrieulacoste.noe.al.trademe.configuration;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.larrieulacoste.noe.al.trademe.domain.model.Amount;
import org.larrieulacoste.noe.al.trademe.features.payment.domain.MembersSubscriptionAmount;
import org.larrieulacoste.noe.al.trademe.kernel.logger.DefaultLoggerFactory;
import org.larrieulacoste.noe.al.trademe.kernel.logger.Logger;
import org.larrieulacoste.noe.al.trademe.kernel.logger.LoggerFactory;
import org.larrieulacoste.noe.al.trademe.kernel.logger.LoggerQualifier;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

@Dependent
final class GlobalConfiguration {

    private final LoggerFactory loggerFactory = new DefaultLoggerFactory();

    @ConfigProperty(name = "contractor.payment.monthly.amount")
    double contractorMonthlySubscriptionAmount;
    @ConfigProperty(name = "tradesman.payment.monthly.amount")
    double tradesmanMonthlySubscriptionAmount;

    @Produces
    MembersSubscriptionAmount membersSubscriptionAmount() {
        return MembersSubscriptionAmount.of(
                Amount.of(contractorMonthlySubscriptionAmount),
                Amount.of(tradesmanMonthlySubscriptionAmount)
        );
    }

    @Produces
    @LoggerQualifier(Logger.class)
    Logger logger(InjectionPoint injectionPoint) {
        Class<?> injectionPointClass = injectionPoint.getAnnotated().getAnnotation(LoggerQualifier.class).value();
        return loggerFactory.getLogger(injectionPointClass != null ? injectionPointClass : injectionPoint.getClass());
    }

    @Produces
    Logger loggerWithoutQualifier(InjectionPoint injectionPoint) {
        return loggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass());
    }
}
