package org.larrieulacoste.noe.al.trademe.features.invoices.application;

import org.larrieulacoste.noe.al.trademe.domain.event.TradesmanDeleted;
import org.larrieulacoste.noe.al.trademe.features.invoices.application.command.DeleteTradesmanInvoices;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

public final class TradesmanDeletedListener implements EventSubscriber<TradesmanDeleted> {

    private final CommandBus commandBus;

    public TradesmanDeletedListener(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public void accept(TradesmanDeleted event) {
        commandBus.send(new DeleteTradesmanInvoices(event.tradesmanId()));
    }
}
