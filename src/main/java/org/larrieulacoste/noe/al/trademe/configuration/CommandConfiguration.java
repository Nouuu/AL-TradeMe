package org.larrieulacoste.noe.al.trademe.configuration;

import org.larrieulacoste.noe.al.trademe.features.members.application.command.CreateContractor;
import org.larrieulacoste.noe.al.trademe.features.members.application.command.CreateContractorService;
import org.larrieulacoste.noe.al.trademe.features.members.application.command.CreateTradesman;
import org.larrieulacoste.noe.al.trademe.features.members.application.command.CreateTradesmanService;
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

    @ApplicationScoped
    CommandBus commandBus() {
        Map<Class<? extends Command>, CommandHandler<? extends Command, ?>> commandMap = new HashMap<>();

        // Members feature
        commandMap.put(CreateContractor.class, createContractorService);
        commandMap.put(CreateTradesman.class, createTradesmanService);

        // Payment feature
        commandMap.put(ContractorSubscriptionPayment.class, contractorSubscriptionPaymentService);
        commandMap.put(TradesmanSubscriptionPayment.class, tradesmanSubscriptionPaymentService);

        return new DefaultCommandBus(commandMap);
    }

}
