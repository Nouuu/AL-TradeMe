package org.larrieulacoste.noe.al.trademe.features.payment.kernel;

import org.larrieulacoste.noe.al.trademe.kernel.command.Command;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.command.DefaultCommandBus;

import java.util.Map;

public class DefaultPaymentCommandBus extends DefaultCommandBus implements PaymentCommandBus {
    public DefaultPaymentCommandBus(Map<Class<? extends Command>, CommandHandler<? extends Command, ?>> commandMap) {
        super(commandMap);
    }
}
