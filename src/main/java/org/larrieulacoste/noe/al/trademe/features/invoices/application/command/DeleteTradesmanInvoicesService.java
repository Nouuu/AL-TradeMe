package org.larrieulacoste.noe.al.trademe.features.invoices.application.command;

import org.larrieulacoste.noe.al.trademe.application.event.InvoiceEventEntity;
import org.larrieulacoste.noe.al.trademe.application.event.TradesmanInvoiceDeleted;
import org.larrieulacoste.noe.al.trademe.domain.model.MemberType;
import org.larrieulacoste.noe.al.trademe.features.invoices.domain.Invoice;
import org.larrieulacoste.noe.al.trademe.features.invoices.domain.Invoices;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class DeleteTradesmanInvoicesService implements CommandHandler<DeleteTradesmanInvoices, Void> {
    private final Invoices invoices;
    private final EventBus<ApplicationEvent> eventBus;

    DeleteTradesmanInvoicesService(Invoices invoices, EventBus<ApplicationEvent> eventBus) {
        this.invoices = invoices;
        this.eventBus = eventBus;
    }

    @Override
    public Void handle(DeleteTradesmanInvoices command) {
        List<Invoice> invoicesToDelete = invoices.getTradesmanInvoices(command.tradesmanId);
        for (Invoice invoiceToDelete : invoicesToDelete) {
            invoices.remove(invoiceToDelete);
            eventBus.publish(TradesmanInvoiceDeleted.of(
                    InvoiceEventEntity.of(invoiceToDelete.invoiceId, MemberType.TRADESMAN, command.tradesmanId, null, null, null)
            ));
        }
        return null;
    }
}
