package org.larrieulacoste.noe.al.trademe.configuration;

import org.larrieulacoste.noe.al.trademe.features.members.application.CreateContractor;
import org.larrieulacoste.noe.al.trademe.features.members.application.CreateContractorService;
import org.larrieulacoste.noe.al.trademe.features.members.application.CreateTradesman;
import org.larrieulacoste.noe.al.trademe.features.members.application.CreateTradesmanService;
import org.larrieulacoste.noe.al.trademe.features.payment.application.ContractorPayment;
import org.larrieulacoste.noe.al.trademe.features.payment.application.ContractorProcessPaymentService;
import org.larrieulacoste.noe.al.trademe.features.payment.application.TradesmanPayment;
import org.larrieulacoste.noe.al.trademe.features.payment.application.TradesmanProcessPaymentService;
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
    ContractorProcessPaymentService contractorProcessPaymentService;
    @Inject
    TradesmanProcessPaymentService tradesmanProcessPaymentService;

    @ApplicationScoped
    CommandBus commandBus() {
        Map<Class<? extends Command>, CommandHandler<? extends Command, ?>> commandMap = new HashMap<>();

        // Members feature
        commandMap.put(CreateContractor.class, createContractorService);
        commandMap.put(CreateTradesman.class, createTradesmanService);

        // Payment feature
        commandMap.put(ContractorPayment.class, contractorProcessPaymentService);
        commandMap.put(TradesmanPayment.class, tradesmanProcessPaymentService);

        return new DefaultCommandBus(commandMap);
    }

}
