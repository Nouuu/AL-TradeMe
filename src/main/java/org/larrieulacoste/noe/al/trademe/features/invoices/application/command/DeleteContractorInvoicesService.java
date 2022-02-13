package org.larrieulacoste.noe.al.trademe.features.invoices.application.command;

import org.larrieulacoste.noe.al.trademe.application.event.ContractorInvoiceDeleted;
import org.larrieulacoste.noe.al.trademe.application.event.InvoiceEventEntity;
import org.larrieulacoste.noe.al.trademe.domain.model.MemberType;
import org.larrieulacoste.noe.al.trademe.features.invoices.domain.Invoice;
import org.larrieulacoste.noe.al.trademe.features.invoices.domain.Invoices;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class DeleteContractorInvoicesService implements CommandHandler<DeleteContractorInvoices, Void> {
    private final Invoices invoices;
    private final EventBus<ApplicationEvent> eventBus;

    DeleteContractorInvoicesService(Invoices invoices, EventBus<ApplicationEvent> eventBus) {
        this.invoices = invoices;
        this.eventBus = eventBus;
    }

    @Override
    public Void handle(DeleteContractorInvoices command) {
        List<Invoice> invoicesToDelete = invoices.getContractorInvoices(command.contractorId());
        for (Invoice invoiceToDelete : invoicesToDelete) {
            invoices.remove(invoiceToDelete);
            eventBus.publish(ContractorInvoiceDeleted.of(
                    InvoiceEventEntity.of(invoiceToDelete.invoiceId(), MemberType.CONTRACTOR, command.contractorId(), null, null, null)
            ));
        }
        return null;
    }
}
