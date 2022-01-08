package org.larrieulacoste.noe.al.trademe.features.invoices.infrastructure;

import org.larrieulacoste.noe.al.trademe.application.exception.InvoiceNotFoundException;
import org.larrieulacoste.noe.al.trademe.application.exception.UserNotFoundException;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.domain.model.MemberType;
import org.larrieulacoste.noe.al.trademe.features.invoices.domain.Invoice;
import org.larrieulacoste.noe.al.trademe.features.invoices.domain.Invoices;
import org.larrieulacoste.noe.al.trademe.kernel.logger.Logger;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public final class InMemoryInvoices implements Invoices {
    private final AtomicInteger counter = new AtomicInteger(0);
    private final Map<EntityId, Invoice> data = new ConcurrentHashMap<>();
    private final Logger logger;

    public InMemoryInvoices(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void save(Invoice invoice) {
        logger.log("Saving invoice in memory repository : " + invoice);

        data.put(Objects.requireNonNull(invoice).invoiceId, invoice);
    }

    @Override
    public Invoice byId(EntityId entityId) throws InvoiceNotFoundException {
        logger.log("Retrieving invoice by ID from memory repository : " + entityId);

        final Invoice invoice = data.get(Objects.requireNonNull(entityId));
        if (invoice == null) {
            throw new UserNotFoundException("No invoice for " + entityId.value);
        }
        return invoice;
    }

    @Override
    public List<Invoice> findAll() {
        return List.copyOf(data.values());
    }

    @Override
    public void remove(Invoice item) {
        data.remove(item.invoiceId);
    }

    @Override
    public EntityId nextId() {
        return EntityId.of(String.valueOf(counter.incrementAndGet()));
    }

    @Override
    public List<Invoice> getTradesmenInvoices() {
        return List.copyOf(
                data.values().stream()
                        .filter(invoice -> invoice.memberType.equals(MemberType.TRADESMAN))
                        .collect(Collectors.toList())
        );
    }

    @Override
    public List<Invoice> getContractorsInvoices() {
        return List.copyOf(
                data.values().stream()
                        .filter(invoice -> invoice.memberType.equals(MemberType.CONTRACTOR))
                        .collect(Collectors.toList())
        );
    }

    @Override
    public List<Invoice> getTradesmanInvoices(EntityId tradesmanId) {
        return List.copyOf(
                data.values().stream()
                        .filter(invoice -> invoice.memberType.equals(MemberType.TRADESMAN)
                                && invoice.memberId.equals(tradesmanId))
                        .collect(Collectors.toList())
        );
    }

    @Override
    public List<Invoice> getContractorInvoices(EntityId contractorId) {
        return List.copyOf(
                data.values().stream()
                        .filter(invoice -> invoice.memberType.equals(MemberType.CONTRACTOR)
                                && invoice.memberId.equals(contractorId))
                        .collect(Collectors.toList())
        );
    }
}
