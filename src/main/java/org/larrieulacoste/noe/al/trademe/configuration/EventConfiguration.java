package org.larrieulacoste.noe.al.trademe.configuration;

import org.larrieulacoste.noe.al.trademe.application.event.*;
import org.larrieulacoste.noe.al.trademe.features.members.application.*;
import org.larrieulacoste.noe.al.trademe.features.payment.application.ContractorsSubscriptionPendingPaymentListener;
import org.larrieulacoste.noe.al.trademe.features.payment.application.NewContractorRegisteredListener;
import org.larrieulacoste.noe.al.trademe.features.payment.application.NewTradesmanRegisteredListener;
import org.larrieulacoste.noe.al.trademe.features.payment.application.TradesmenSubscriptionPendingPaymentListener;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.DefaultEventBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class EventConfiguration {

    @Inject
    CommandBus commandBus;


    @ApplicationScoped
    EventBus<ApplicationEvent> applicationEventBus() {
        final EventBus<ApplicationEvent> eventBus = new DefaultEventBus<>();

        // Members feature
        eventBus.register(NewContractorRegistration.class, new NewContractorRegistrationListener(commandBus));
        eventBus.register(NewTradesmanRegistration.class, new NewTradesmenRegistrationListener(commandBus));

        eventBus.register(NewContractorSubscriptionPayment.class, new NewContractorSubscriptionPaymentListener(commandBus));
        eventBus.register(NewTradesmanSubscriptionPayment.class, new NewTradesmanSubscriptionPaymentListener(commandBus));

        eventBus.register(MonthlySubscriptionPayment.class, new MonthlySubscriptionPaymentListener(commandBus));

        // Payment feature
        eventBus.register(NewContractorRegistered.class, new NewContractorRegisteredListener(commandBus));
        eventBus.register(NewTradesmanRegistered.class, new NewTradesmanRegisteredListener(commandBus));

        eventBus.register(ContractorsSubscriptionPendingPayment.class, new ContractorsSubscriptionPendingPaymentListener(commandBus));
        eventBus.register(TradesmenSubscriptionPendingPayment.class, new TradesmenSubscriptionPendingPaymentListener(commandBus));

        return eventBus;
    }
}
