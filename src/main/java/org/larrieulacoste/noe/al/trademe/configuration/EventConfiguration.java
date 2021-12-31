package org.larrieulacoste.noe.al.trademe.configuration;

import org.larrieulacoste.noe.al.trademe.application.event.*;
import org.larrieulacoste.noe.al.trademe.features.members.application.NewContractorPaymentListener;
import org.larrieulacoste.noe.al.trademe.features.members.application.NewContractorRegistrationListener;
import org.larrieulacoste.noe.al.trademe.features.members.application.NewTradesmanPaymentListener;
import org.larrieulacoste.noe.al.trademe.features.members.application.NewTradesmenRegistrationListener;
import org.larrieulacoste.noe.al.trademe.features.payment.application.NewContractorRegisteredListener;
import org.larrieulacoste.noe.al.trademe.features.payment.application.NewTradesmanRegisteredListener;
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
        eventBus.register(NewContractorSubscriptionPayment.class, new NewContractorPaymentListener(commandBus));
        eventBus.register(NewTradesmanSubscriptionPayment.class, new NewTradesmanPaymentListener(commandBus));

        // Payment feature
        eventBus.register(NewContractorRegistered.class, new NewContractorRegisteredListener(commandBus));
        eventBus.register(NewTradesmanRegistered.class, new NewTradesmanRegisteredListener(commandBus));

        return eventBus;
    }
}
