package org.larrieulacoste.noe.al.trademe.features.invoices.infrastructure;

import org.larrieulacoste.noe.al.trademe.features.invoices.domain.Invoice;
import org.larrieulacoste.noe.al.trademe.features.invoices.domain.Invoices;
import org.larrieulacoste.noe.al.trademe.kernel.exception.UserNotFoundException;
import org.larrieulacoste.noe.al.trademe.kernel.io.Reader;
import org.larrieulacoste.noe.al.trademe.kernel.io.Writer;
import org.larrieulacoste.noe.al.trademe.kernel.marshaller.DeserializationEngine;
import org.larrieulacoste.noe.al.trademe.kernel.marshaller.SerializationEngine;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.EntityId;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public final class InFileInvoices implements Invoices {
    private final InMemoryInvoices inMemoryInvoices;
    private final AtomicInteger counter = new AtomicInteger(0);
    private final SerializationEngine serializer;
    private final DeserializationEngine deserializer;
    private final Reader reader;
    private final Writer writer;

    public InFileInvoices(InMemoryInvoices inMemoryInvoices,
                          SerializationEngine serializer,
                          DeserializationEngine deserializer,
                          Reader reader,
                          Writer writer) {
        this.inMemoryInvoices = inMemoryInvoices;
        this.serializer = serializer;
        this.deserializer = deserializer;
        this.reader = reader;
        this.writer = writer;
        this.read();
    }

    @Override
    public void save(Invoice invoice) {
        inMemoryInvoices.save(invoice);
        this.write();
    }

    @Override
    public Invoice byId(EntityId entityId) throws UserNotFoundException {
        return inMemoryInvoices.byId(entityId);
    }

    @Override
    public List<Invoice> findAll() {
        return inMemoryInvoices.findAll();
    }

    @Override
    public void remove(Invoice item) {
        inMemoryInvoices.remove(item);
        this.write();
    }

    @Override
    public EntityId nextId() {
        return EntityId.of(String.valueOf(counter.incrementAndGet()));
    }

    @Override
    public List<Invoice> getTradesmenInvoices() {
        return inMemoryInvoices.getTradesmenInvoices();
    }

    @Override
    public List<Invoice> getContractorsInvoices() {
        return inMemoryInvoices.getContractorsInvoices();
    }

    @Override
    public List<Invoice> getTradesmanInvoices(EntityId tradesmanId) {
        return inMemoryInvoices.getTradesmanInvoices(tradesmanId);
    }

    @Override
    public List<Invoice> getContractorInvoices(EntityId contractorId) {
        return inMemoryInvoices.getContractorInvoices(contractorId);
    }


    private void write() {
        var data = serializer.apply(inMemoryInvoices.findAll());
        writer.write(data);
    }

    private void read() {
        var data = deserializer.apply(reader.read(), Invoice[].class);
        if (data != null) {
            for (Invoice invoice : data) {
                inMemoryInvoices.save(invoice);
                this.counter.set(Integer.parseInt(invoice.invoiceId().value()));
            }
        }
    }
}
