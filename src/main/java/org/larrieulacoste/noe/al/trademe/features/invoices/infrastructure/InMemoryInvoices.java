package org.larrieulacoste.noe.al.trademe.features.invoices.infrastructure;

import org.larrieulacoste.noe.al.trademe.application.exception.InvoiceNotFoundException;
import org.larrieulacoste.noe.al.trademe.application.exception.UserNotFoundException;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.features.invoices.domain.Invoice;
import org.larrieulacoste.noe.al.trademe.features.invoices.domain.Invoices;
import org.larrieulacoste.noe.al.trademe.kernel.logger.Logger;
import org.larrieulacoste.noe.al.trademe.kernel.logger.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public final class InMemoryInvoices implements Invoices {
    private final AtomicInteger counter = new AtomicInteger(0);
    private final Map<EntityId, Invoice> data = new ConcurrentHashMap<>();
    private final Logger logger;

    public InMemoryInvoices() {
        this.logger = LoggerFactory.getLogger(this);
    }

    @Override
    public void save(Invoice invoice) {
        logger.log("Saving invoice in memory repository : " + invoice);

        data.put(Objects.requireNonNull(invoice).getInvoiceId(), invoice);
    }

    @Override
    public Invoice byId(EntityId entityId) throws InvoiceNotFoundException {
        logger.log("Retrieving invoice by ID from memory repository : " + entityId);

        final Invoice invoice = data.get(Objects.requireNonNull(entityId));
        if (invoice == null) {
            throw new UserNotFoundException("No invoice for " + entityId.getValue());
        }
        return invoice;
    }

    @Override
    public List<Invoice> findAll() {
        return List.copyOf(data.values());
    }

    @Override
    public EntityId nextId() {
        return EntityId.of(String.valueOf(counter.incrementAndGet()));
    }
}
