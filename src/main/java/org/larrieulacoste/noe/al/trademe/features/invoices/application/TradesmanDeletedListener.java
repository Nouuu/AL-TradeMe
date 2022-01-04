package org.larrieulacoste.noe.al.trademe.features.invoices.application;

import org.larrieulacoste.noe.al.trademe.application.event.TradesmanDeleted;
import org.larrieulacoste.noe.al.trademe.features.invoices.application.command.DeleteTradesmanInvoices;
import org.larrieulacoste.noe.al.trademe.features.invoices.kernel.InvoicesCommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

public class TradesmanDeletedListener implements EventSubscriber<TradesmanDeleted> {

    private final InvoicesCommandBus commandBus;

    public TradesmanDeletedListener(InvoicesCommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public void accept(TradesmanDeleted event) {
        commandBus.send(new DeleteTradesmanInvoices(event.getTradesman().entityId));
    }
}
