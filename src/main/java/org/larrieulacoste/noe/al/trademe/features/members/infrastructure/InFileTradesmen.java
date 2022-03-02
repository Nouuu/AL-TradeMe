package org.larrieulacoste.noe.al.trademe.features.members.infrastructure;

import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesman;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesmen;
import org.larrieulacoste.noe.al.trademe.kernel.exception.UserNotFoundException;
import org.larrieulacoste.noe.al.trademe.kernel.io.Reader;
import org.larrieulacoste.noe.al.trademe.kernel.io.Writer;
import org.larrieulacoste.noe.al.trademe.kernel.serializer.DeserializationEngine;
import org.larrieulacoste.noe.al.trademe.kernel.serializer.SerializationEngine;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.EntityId;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public final class InFileTradesmen implements Tradesmen {
    private final InMemoryTradesmen inMemoryTradesmen;
    private final AtomicInteger counter = new AtomicInteger(0);
    private final SerializationEngine serializer;
    private final DeserializationEngine deserializer;
    private final Reader reader;
    private final Writer writer;

    public InFileTradesmen(InMemoryTradesmen inMemoryTradesmen,
                           SerializationEngine serializer,
                           DeserializationEngine deserializer,
                           Reader reader,
                           Writer writer) {
        this.inMemoryTradesmen = inMemoryTradesmen;
        this.serializer = serializer;
        this.deserializer = deserializer;
        this.reader = reader;
        this.writer = writer;
        this.read();
    }

    @Override
    public void save(Tradesman tradesman) {
        inMemoryTradesmen.save(tradesman);
        this.write();
    }

    @Override
    public Tradesman byId(EntityId entityId) throws UserNotFoundException {
        return inMemoryTradesmen.byId(entityId);
    }

    @Override
    public List<Tradesman> findAll() {
        return inMemoryTradesmen.findAll();
    }

    @Override
    public void remove(Tradesman item) {
        inMemoryTradesmen.remove(item);
        this.write();
    }

    @Override
    public EntityId nextId() {
        return EntityId.of(String.valueOf(counter.incrementAndGet()));
    }


    private void write() {
        var data = serializer.apply(inMemoryTradesmen.findAll());
        writer.write(data);
    }

    private void read() {
        var data = deserializer.apply(reader.read(), Tradesman[].class);
        if (data != null) {
            for (Tradesman tradesman : data) {
                inMemoryTradesmen.save(tradesman);
                this.counter.set(Integer.parseInt(tradesman.entityId().value()));
            }
        }
    }
}
