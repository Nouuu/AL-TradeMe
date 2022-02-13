package org.larrieulacoste.noe.al.trademe.configuration;

import org.larrieulacoste.noe.al.trademe.features.invoices.application.command.*;
import org.larrieulacoste.noe.al.trademe.features.members.application.command.*;
import org.larrieulacoste.noe.al.trademe.features.payment.application.command.ContractorSubscriptionPayment;
import org.larrieulacoste.noe.al.trademe.features.payment.application.command.ContractorSubscriptionPaymentService;
import org.larrieulacoste.noe.al.trademe.features.payment.application.command.TradesmanSubscriptionPayment;
import org.larrieulacoste.noe.al.trademe.features.payment.application.command.TradesmanSubscriptionPaymentService;
import org.larrieulacoste.noe.al.trademe.kernel.command.Command;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.command.DefaultCommandBus;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Dependent
final class CommandConfiguration {
    @Inject
    CreateContractorService createContractorService;
    @Inject
    CreateTradesmanService createTradesmanService;
    @Inject
    UpdateContractorService updateContractorService;
    @Inject
    UpdateTradesmanService updateTradesmanService;
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
    @Inject
    DeleteTradesmanService deleteTradesmanService;
    @Inject
    DeleteContractorService deleteContractorService;
    @Inject
    DeleteTradesmanInvoicesService deleteTradesmanInvoicesService;
    @Inject
    DeleteContractorInvoicesService deleteContractorInvoicesService;

    @Produces
    @Singleton
    CommandBus commandBus() {
        Map<Class<? extends Command>, CommandHandler<? extends Command, ?>> commandMap = new HashMap<>();

        // Invoices
        commandMap.put(CreateInvoice.class, createInvoiceService);
        commandMap.put(DeleteTradesmanInvoices.class, deleteTradesmanInvoicesService);
        commandMap.put(DeleteContractorInvoices.class, deleteContractorInvoicesService);

        // Members
        commandMap.put(CreateContractor.class, createContractorService);
        commandMap.put(CreateTradesman.class, createTradesmanService);
        commandMap.put(UpdateContractor.class, updateContractorService);
        commandMap.put(UpdateTradesman.class, updateTradesmanService);
        commandMap.put(DeleteTradesman.class, deleteTradesmanService);
        commandMap.put(DeleteContractor.class, deleteContractorService);
        commandMap.put(PublishContractorsPendingSubscriptionPayment.class, publishContractorsPendingSubscriptionPaymentService);
        commandMap.put(PublishTradesmenPendingSubscriptionPayment.class, publishTradesmenPendingSubscriptionPaymentService);
        commandMap.put(UpdateContractorSubscriptionStatus.class, updateContractorSubscriptionStatusService);
        commandMap.put(UpdateTradesmanSubscriptionStatus.class, updateTradesmanSubscriptionStatusService);

        // Payments
        commandMap.put(ContractorSubscriptionPayment.class, contractorSubscriptionPaymentService);
        commandMap.put(TradesmanSubscriptionPayment.class, tradesmanSubscriptionPaymentService);

        return new DefaultCommandBus(commandMap);
    }
}
