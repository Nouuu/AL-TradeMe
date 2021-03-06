package org.larrieulacoste.noe.al.trademe.features.invoices.infrastructure;

import org.larrieulacoste.noe.al.trademe.features.invoices.domain.Invoice;
import org.larrieulacoste.noe.al.trademe.features.invoices.domain.Invoices;
import org.larrieulacoste.noe.al.trademe.kernel.exception.InvoiceNotFoundException;
import org.larrieulacoste.noe.al.trademe.kernel.logger.Logger;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.EntityId;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.MemberType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

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

        data.put(Objects.requireNonNull(invoice).invoiceId(), invoice);
    }

    @Override
    public Invoice byId(EntityId entityId) throws InvoiceNotFoundException {
        logger.log("Retrieving invoice by ID from memory repository : " + entityId);

        final Invoice invoice = data.get(Objects.requireNonNull(entityId));
        if (invoice == null) {
            throw new InvoiceNotFoundException("No invoice for " + entityId.value());
        }
        return invoice;
    }

    @Override
    public List<Invoice> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public void remove(Invoice item) {
        data.remove(item.invoiceId());
    }

    @Override
    public EntityId nextId() {
        return EntityId.of(String.valueOf(counter.incrementAndGet()));
    }

    @Override
    public List<Invoice> getTradesmenInvoices() {
        return new ArrayList<>(
                data.values().stream()
                        .filter(invoice -> invoice.memberType().equals(MemberType.TRADESMAN))
                        .toList()
        );
    }

    @Override
    public List<Invoice> getContractorsInvoices() {
        return new ArrayList<>(
                data.values().stream()
                        .filter(invoice -> invoice.memberType().equals(MemberType.CONTRACTOR))
                        .toList()
        );
    }

    @Override
    public List<Invoice> getTradesmanInvoices(EntityId tradesmanId) {
        return new ArrayList<>(
                data.values().stream()
                        .filter(invoice -> invoice.memberType().equals(MemberType.TRADESMAN)
                                && invoice.memberId().equals(tradesmanId))
                        .toList()
        );
    }

    @Override
    public List<Invoice> getContractorInvoices(EntityId contractorId) {
        return new ArrayList<>(
                data.values().stream()
                        .filter(invoice -> invoice.memberType().equals(MemberType.CONTRACTOR)
                                && invoice.memberId().equals(contractorId))
                        .toList()
        );
    }
}
