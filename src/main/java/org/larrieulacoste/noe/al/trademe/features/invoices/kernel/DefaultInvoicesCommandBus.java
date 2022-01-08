package org.larrieulacoste.noe.al.trademe.features.invoices.kernel;

import org.larrieulacoste.noe.al.trademe.kernel.command.Command;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.command.DefaultCommandBus;

import java.util.Map;

public final class DefaultInvoicesCommandBus extends DefaultCommandBus implements InvoicesCommandBus {
    public DefaultInvoicesCommandBus(Map<Class<? extends Command>, CommandHandler<? extends Command, ?>> commandMap) {
        super(commandMap);
    }
}
