package org.larrieulacoste.noe.al.trademe.configuration;

import org.larrieulacoste.noe.al.trademe.features.invoices.application.command.CreateInvoice;
import org.larrieulacoste.noe.al.trademe.features.invoices.application.command.CreateInvoiceService;
import org.larrieulacoste.noe.al.trademe.features.invoices.kernel.DefaultInvoicesCommandBus;
import org.larrieulacoste.noe.al.trademe.features.invoices.kernel.InvoicesCommandBus;
import org.larrieulacoste.noe.al.trademe.features.members.application.command.*;
import org.larrieulacoste.noe.al.trademe.features.members.kernel.DefaultMembersCommandBus;
import org.larrieulacoste.noe.al.trademe.features.members.kernel.MembersCommandBus;
import org.larrieulacoste.noe.al.trademe.features.payment.application.command.ContractorSubscriptionPayment;
import org.larrieulacoste.noe.al.trademe.features.payment.application.command.ContractorSubscriptionPaymentService;
import org.larrieulacoste.noe.al.trademe.features.payment.application.command.TradesmanSubscriptionPayment;
import org.larrieulacoste.noe.al.trademe.features.payment.application.command.TradesmanSubscriptionPaymentService;
import org.larrieulacoste.noe.al.trademe.features.payment.kernel.DefaultPaymentCommandBus;
import org.larrieulacoste.noe.al.trademe.features.payment.kernel.PaymentCommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.command.Command;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@Dependent
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


    @Produces
    InvoicesCommandBus invoicesCommandBus() {
        Map<Class<? extends Command>, CommandHandler<? extends Command, ?>> commandMap = new HashMap<>();

        commandMap.put(CreateInvoice.class, createInvoiceService);

        return new DefaultInvoicesCommandBus(commandMap);
    }

    @Produces
    MembersCommandBus membersCommandBus() {
        Map<Class<? extends Command>, CommandHandler<? extends Command, ?>> commandMap = new HashMap<>();

        commandMap.put(CreateContractor.class, createContractorService);
        commandMap.put(CreateTradesman.class, createTradesmanService);
        commandMap.put(PublishContractorsPendingSubscriptionPayment.class, publishContractorsPendingSubscriptionPaymentService);
        commandMap.put(PublishTradesmenPendingSubscriptionPayment.class, publishTradesmenPendingSubscriptionPaymentService);
        commandMap.put(UpdateContractorSubscriptionStatus.class, updateContractorSubscriptionStatusService);
        commandMap.put(UpdateTradesmanSubscriptionStatus.class, updateTradesmanSubscriptionStatusService);

        return new DefaultMembersCommandBus(commandMap);
    }

    @Produces
    PaymentCommandBus paymentCommandBus() {
        Map<Class<? extends Command>, CommandHandler<? extends Command, ?>> commandMap = new HashMap<>();

        commandMap.put(ContractorSubscriptionPayment.class, contractorSubscriptionPaymentService);
        commandMap.put(TradesmanSubscriptionPayment.class, tradesmanSubscriptionPaymentService);

        return new DefaultPaymentCommandBus(commandMap);
    }

}
