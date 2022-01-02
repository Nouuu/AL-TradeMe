package org.larrieulacoste.noe.al.trademe.configuration;

import org.larrieulacoste.noe.al.trademe.features.invoices.application.command.CreateInvoice;
import org.larrieulacoste.noe.al.trademe.features.invoices.application.command.CreateInvoiceService;
import org.larrieulacoste.noe.al.trademe.features.members.application.command.*;
import org.larrieulacoste.noe.al.trademe.features.payment.application.command.ContractorSubscriptionPayment;
import org.larrieulacoste.noe.al.trademe.features.payment.application.command.ContractorSubscriptionPaymentService;
import org.larrieulacoste.noe.al.trademe.features.payment.application.command.TradesmanSubscriptionPayment;
import org.larrieulacoste.noe.al.trademe.features.payment.application.command.TradesmanSubscriptionPaymentService;
import org.larrieulacoste.noe.al.trademe.kernel.command.Command;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.command.DefaultCommandBus;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class CommandConfiguration {
    @Inject
    CreateContractorService createContractorService;
    @Inject
    CreateTradesmanService createTradesmanService;
    @Inject
    ContractorSubscriptionPaymentService contractorSubscriptionPaymentService;
    @Inject
    TradesmanSubscriptionPaymentService tradesmanSubscriptionPaymentService;
    @Inject
    PublishContractorsPendingSubscriptionPaymentService publishContractorsPendingSubscriptionPaymentService;
    @Inject
    PublishTradesmenPendingSubscriptionPaymentService publishTradesmenPendingSubscriptionPaymentService;
    @Inject
    CreateInvoiceService createInvoiceService;
    @Inject
    UpdateContractorSubscriptionStatusService updateContractorSubscriptionStatusService;
    @Inject
    UpdateTradesmanSubscriptionStatusService updateTradesmanSubscriptionStatusService;

    @ApplicationScoped
    CommandBus commandBus() {
        Map<Class<? extends Command>, CommandHandler<? extends Command, ?>> commandMap = new HashMap<>();

        // Members feature
        commandMap.put(CreateContractor.class, createContractorService);
        commandMap.put(CreateTradesman.class, createTradesmanService);
        commandMap.put(PublishContractorsPendingSubscriptionPayment.class, publishContractorsPendingSubscriptionPaymentService);
        commandMap.put(PublishTradesmenPendingSubscriptionPayment.class, publishTradesmenPendingSubscriptionPaymentService);
        commandMap.put(UpdateContractorSubscriptionStatus.class, updateContractorSubscriptionStatusService);
        commandMap.put(UpdateTradesmanSubscriptionStatus.class, updateTradesmanSubscriptionStatusService);

        // Payment feature
        commandMap.put(ContractorSubscriptionPayment.class, contractorSubscriptionPaymentService);
        commandMap.put(TradesmanSubscriptionPayment.class, tradesmanSubscriptionPaymentService);

        // Invoices feature
        commandMap.put(CreateInvoice.class, createInvoiceService);

        return new DefaultCommandBus(commandMap);
    }

}
