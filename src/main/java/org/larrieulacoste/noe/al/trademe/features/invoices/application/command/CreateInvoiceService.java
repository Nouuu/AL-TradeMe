package org.larrieulacoste.noe.al.trademe.features.invoices.application.command;

import org.larrieulacoste.noe.al.trademe.application.event.InvoiceEventEntity;
import org.larrieulacoste.noe.al.trademe.application.event.ContractorInvoiceCreated;
import org.larrieulacoste.noe.al.trademe.application.event.TradesmanNewInvoice;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.domain.model.MemberType;
import org.larrieulacoste.noe.al.trademe.features.invoices.domain.Invoice;
import org.larrieulacoste.noe.al.trademe.features.invoices.domain.Invoices;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;

import javax.enterprise.context.ApplicationScoped;
import java.time.ZonedDateTime;

@ApplicationScoped
public class CreateInvoiceService implements CommandHandler<CreateInvoice, EntityId> {
    private final Invoices invoices;
    private final EventBus<ApplicationEvent> eventBus;

    public CreateInvoiceService(Invoices invoices, EventBus<ApplicationEvent> eventBus) {
        this.invoices = invoices;
        this.eventBus = eventBus;
    }

    @Override
    public EntityId handle(CreateInvoice command) {
        final EntityId invoiceId = invoices.nextId();

        Invoice invoice = Invoice.of(
                invoiceId,
                command.memberType,
                command.memberId,
                ZonedDateTime.now(),
                command.amount
        );
        invoices.save(invoice);

        if (command.memberType == MemberType.TRADESMAN) {
            eventBus.publish(TradesmanNewInvoice.of(InvoiceEventEntity.of(
                    invoiceId,
                    MemberType.TRADESMAN,
                    command.memberId,
                    invoice.getOccurredDate(),
                    command.amount
            )));
        } else if (command.memberType == MemberType.CONTRACTOR) {
            eventBus.publish(ContractorInvoiceCreated.of(InvoiceEventEntity.of(
                    invoiceId,
                    MemberType.CONTRACTOR,
                    command.memberId,
                    invoice.getOccurredDate(),
                    command.amount
            )));
        }

        return invoiceId;
    }
}
