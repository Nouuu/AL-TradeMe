package org.larrieulacoste.noe.al.trademe.configuration;

import org.larrieulacoste.noe.al.trademe.domain.event.*;
import org.larrieulacoste.noe.al.trademe.features.invoices.application.ContractorDeletedListener;
import org.larrieulacoste.noe.al.trademe.features.invoices.application.TradesmanDeletedListener;
import org.larrieulacoste.noe.al.trademe.features.members.application.*;
import org.larrieulacoste.noe.al.trademe.features.payment.application.ContractorsSubscriptionPendingPaymentListener;
import org.larrieulacoste.noe.al.trademe.features.payment.application.NewContractorRegisteredListener;
import org.larrieulacoste.noe.al.trademe.features.payment.application.NewTradesmanRegisteredListener;
import org.larrieulacoste.noe.al.trademe.features.payment.application.TradesmenSubscriptionPendingPaymentListener;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.DefaultEventBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;
import org.larrieulacoste.noe.al.trademe.kernel.logger.Logger;
import org.larrieulacoste.noe.al.trademe.kernel.logger.LoggerQualifier;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;

@Dependent
final class EventConfiguration {

    @Inject
    @LoggerQualifier(EventBus.class)
    Logger eventBusLogger;

    @Inject
    CommandBus commandBus;


    @Produces
    @Singleton
    EventBus<ApplicationEvent> applicationEventBus() {
        final EventBus<ApplicationEvent> eventBus = new DefaultEventBus<>(new HashMap<>(), eventBusLogger);

        // Members feature
        eventBus.register(ContractorNewRegistration.class, new NewContractorRegistrationListener(commandBus));
        eventBus.register(TradesmanNewRegistration.class, new NewTradesmenRegistrationListener(commandBus));

        eventBus.register(ContractorNewSubscriptionPayment.class, new NewContractorSubscriptionPaymentListener(commandBus));
        eventBus.register(TradesmanNewSubscriptionPayment.class, new NewTradesmanSubscriptionPaymentListener(commandBus));
        eventBus.register(ContractorSubscriptionPaymentFailure.class, new ContractorSubscriptionPaymentFailureListener(commandBus));
        eventBus.register(TradesmanSubscriptionPaymentFailure.class, new TradesmanSubscriptionPaymentFailureListener(commandBus));

        eventBus.register(MonthlySubscriptionPayment.class, new MonthlySubscriptionPaymentListener(commandBus));

        // Payment feature
        eventBus.register(ContractorRegistered.class, new NewContractorRegisteredListener(commandBus));
        eventBus.register(TradesmanRegistered.class, new NewTradesmanRegisteredListener(commandBus));

        eventBus.register(ContractorsSubscriptionPendingPayment.class, new ContractorsSubscriptionPendingPaymentListener(commandBus));
        eventBus.register(TradesmenSubscriptionPendingPayment.class, new TradesmenSubscriptionPendingPaymentListener(commandBus));

        // Invoices feature
        eventBus.register(ContractorNewSubscriptionPayment.class, new org.larrieulacoste.noe.al.trademe.features.invoices.application.NewContractorSubscriptionPaymentListener(commandBus));
        eventBus.register(TradesmanNewSubscriptionPayment.class, new org.larrieulacoste.noe.al.trademe.features.invoices.application.NewTradesmanSubscriptionPaymentListener(commandBus));
        eventBus.register(ContractorDeleted.class, new ContractorDeletedListener(commandBus));
        eventBus.register(TradesmanDeleted.class, new TradesmanDeletedListener(commandBus));

        // Project feature
        eventBus.register(TradesmanTerminated.class, new TradesmanTerminatedListener(commandBus));
        eventBus.register(TradesmanAssigned.class, new TradesmanAssignedListener(commandBus));
        return eventBus;
    }
}
