package org.larrieulacoste.noe.al.trademe.configuration;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.larrieulacoste.noe.al.trademe.domain.model.Amount;
import org.larrieulacoste.noe.al.trademe.features.members.domain.TradesmanBuilder;
import org.larrieulacoste.noe.al.trademe.features.payment.domain.MembersSubscriptionAmount;
import org.larrieulacoste.noe.al.trademe.kernel.logger.JBossLoggerFactory;
import org.larrieulacoste.noe.al.trademe.kernel.logger.Logger;
import org.larrieulacoste.noe.al.trademe.kernel.logger.LoggerFactory;
import org.larrieulacoste.noe.al.trademe.kernel.logger.LoggerQualifier;
import org.larrieulacoste.noe.al.trademe.kernel.validators.*;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Singleton;

@Dependent
final class GlobalConfiguration {

    private final LoggerFactory loggerFactory = new JBossLoggerFactory();

    @ConfigProperty(name = "contractor.payment.monthly.amount")
    double contractorMonthlySubscriptionAmount;
    @ConfigProperty(name = "tradesman.payment.monthly.amount")
    double tradesmanMonthlySubscriptionAmount;

    @Produces
    @Singleton
    MembersSubscriptionAmount membersSubscriptionAmount() {
        return MembersSubscriptionAmount.of(
                Amount.of(contractorMonthlySubscriptionAmount),
                Amount.of(tradesmanMonthlySubscriptionAmount));
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

    @Produces
    DateValidators dateValidators() {
        return new SimpleDateValidators();
    }

    @Produces
    StringValidators stringValidators() {
        return new SimpleStringValidators();
    }

    @Produces
    PaymentInformationsValidator paymentInformationsValidator() {
        return new SimplePaymentInformationsValidator();
    }

    @Produces
    TradesmanBuilder tradesmanBuilder() {
        return new TradesmanBuilder(stringValidators());
    }
}
