package org.larrieulacoste.noe.al.trademe.configuration;

import org.larrieulacoste.noe.al.trademe.application.event.NewContractorRegistration;
import org.larrieulacoste.noe.al.trademe.application.event.NewTradesmanRegistration;
import org.larrieulacoste.noe.al.trademe.domain.logger.LoggerFactory;
import org.larrieulacoste.noe.al.trademe.features.members.application.NewContractorRegistrationListener;
import org.larrieulacoste.noe.al.trademe.features.members.application.NewTradesmenRegistrationListener;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.DefaultEventBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class EventConfiguration {
    @Inject
    LoggerFactory loggerFactory;
    @Inject
    CommandBus commandBus;


    @ApplicationScoped
    EventBus<ApplicationEvent> applicationEventBus() {
        final EventBus<ApplicationEvent> eventBus = new DefaultEventBus<>(loggerFactory);

        // Members feature
        eventBus.register(NewContractorRegistration.class, new NewContractorRegistrationListener(commandBus));
        eventBus.register(NewTradesmanRegistration.class, new NewTradesmenRegistrationListener(commandBus));
        return eventBus;
    }
}