package org.larrieulacoste.noe.al.trademe.features.members.infrastructure;

import org.larrieulacoste.noe.al.trademe.features.members.domain.Contractor;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Contractors;
import org.larrieulacoste.noe.al.trademe.kernel.exception.UserNotFoundException;
import org.larrieulacoste.noe.al.trademe.kernel.io.Reader;
import org.larrieulacoste.noe.al.trademe.kernel.io.Writer;
import org.larrieulacoste.noe.al.trademe.kernel.serializer.DeserializationEngine;
import org.larrieulacoste.noe.al.trademe.kernel.serializer.SerializationEngine;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.EntityId;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public final class InFileContractors implements Contractors {
    private final InMemoryContractors inMemoryContractors;
    private final AtomicInteger counter = new AtomicInteger(0);
    private final SerializationEngine serializer;
    private final DeserializationEngine deserializer;
    private final Reader reader;
    private final Writer writer;

    public InFileContractors(InMemoryContractors inMemoryContractors,
                             SerializationEngine serializer,
                             DeserializationEngine deserializer,
                             Reader reader,
                             Writer writer) {
        this.inMemoryContractors = inMemoryContractors;
        this.serializer = serializer;
        this.deserializer = deserializer;
        this.reader = reader;
        this.writer = writer;
        this.read();
    }

    @Override
    public void save(Contractor contractor) {
        inMemoryContractors.save(contractor);
        this.write();
    }

    @Override
    public Contractor byId(EntityId entityId) throws UserNotFoundException {
        return inMemoryContractors.byId(entityId);
    }

    @Override
    public List<Contractor> findAll() {
        return inMemoryContractors.findAll();
    }

    @Override
    public void remove(Contractor item) {
        inMemoryContractors.remove(item);
        this.write();
    }

    @Override
    public EntityId nextId() {
        return EntityId.of(String.valueOf(counter.incrementAndGet()));
    }


    private void write() {
        var data = serializer.apply(inMemoryContractors.findAll());
        writer.write(data);
    }

    private void read() {
        var data = deserializer.apply(reader.read(), Contractor[].class);
        if (data != null) {
            for (Contractor contractor : data) {
                inMemoryContractors.save(contractor);
                this.counter.set(Integer.parseInt(contractor.entityId().value()));
            }
        }
    }
}
