package org.larrieulacoste.noe.al.trademe.configuration;

import org.larrieulacoste.noe.al.trademe.application.event.*;
import org.larrieulacoste.noe.al.trademe.features.invoices.kernel.InvoicesCommandBus;
import org.larrieulacoste.noe.al.trademe.features.members.application.*;
import org.larrieulacoste.noe.al.trademe.features.members.kernel.MembersCommandBus;
import org.larrieulacoste.noe.al.trademe.features.payment.application.ContractorsSubscriptionPendingPaymentListener;
import org.larrieulacoste.noe.al.trademe.features.payment.application.NewContractorRegisteredListener;
import org.larrieulacoste.noe.al.trademe.features.payment.application.NewTradesmanRegisteredListener;
import org.larrieulacoste.noe.al.trademe.features.payment.application.TradesmenSubscriptionPendingPaymentListener;
import org.larrieulacoste.noe.al.trademe.features.payment.kernel.PaymentCommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.DefaultEventBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@Dependent
public class EventConfiguration {

    @Inject
    InvoicesCommandBus invoicesCommandBus;

    @Inject
    MembersCommandBus membersCommandBus;

    @Inject
    PaymentCommandBus paymentCommandBus;


    @Produces
    EventBus<ApplicationEvent> applicationEventBus() {
        final EventBus<ApplicationEvent> eventBus = new DefaultEventBus<>();

        // Members feature
        eventBus.register(NewContractorRegistration.class, new NewContractorRegistrationListener(membersCommandBus));
        eventBus.register(NewTradesmanRegistration.class, new NewTradesmenRegistrationListener(membersCommandBus));

        eventBus.register(NewContractorSubscriptionPayment.class, new NewContractorSubscriptionPaymentListener(membersCommandBus));
        eventBus.register(NewTradesmanSubscriptionPayment.class, new NewTradesmanSubscriptionPaymentListener(membersCommandBus));
        eventBus.register(ContractorSubscriptionPaymentFailure.class, new ContractorSubscriptionPaymentFailureListener(membersCommandBus));
        eventBus.register(TradesmanSubscriptionPaymentFailure.class, new TradesmanSubscriptionPaymentFailureListener(membersCommandBus));

        eventBus.register(MonthlySubscriptionPayment.class, new MonthlySubscriptionPaymentListener(membersCommandBus));

        // Payment feature
        eventBus.register(NewContractorRegistered.class, new NewContractorRegisteredListener(paymentCommandBus));
        eventBus.register(NewTradesmanRegistered.class, new NewTradesmanRegisteredListener(paymentCommandBus));

        eventBus.register(ContractorsSubscriptionPendingPayment.class, new ContractorsSubscriptionPendingPaymentListener(paymentCommandBus));
        eventBus.register(TradesmenSubscriptionPendingPayment.class, new TradesmenSubscriptionPendingPaymentListener(paymentCommandBus));

        // Invoices feature
        eventBus.register(NewContractorSubscriptionPayment.class,
                new org.larrieulacoste.noe.al.trademe.features.invoices.application.NewContractorSubscriptionPaymentListener(invoicesCommandBus));
        eventBus.register(NewTradesmanSubscriptionPayment.class,
                new org.larrieulacoste.noe.al.trademe.features.invoices.application.NewTradesmanSubscriptionPaymentListener(invoicesCommandBus));

        return eventBus;
    }
}
