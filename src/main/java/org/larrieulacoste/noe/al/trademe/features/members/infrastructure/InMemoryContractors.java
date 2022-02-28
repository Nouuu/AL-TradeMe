package org.larrieulacoste.noe.al.trademe.features.members.infrastructure;

import java.util.ArrayList;
import org.larrieulacoste.noe.al.trademe.domain.exception.UserNotFoundException;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Contractor;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Contractors;
import org.larrieulacoste.noe.al.trademe.kernel.logger.Logger;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public final class InMemoryContractors implements Contractors {
    private final AtomicInteger counter = new AtomicInteger(0);
    private final Map<EntityId, Contractor> data = new ConcurrentHashMap<>();
    private final Logger logger;

    public InMemoryContractors(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void save(Contractor contractor) {
        logger.log("Saving contractor in memory repository : " + contractor);

        data.put(Objects.requireNonNull(contractor).entityId(), contractor);
    }

    @Override
    public Contractor byId(EntityId entityId) throws UserNotFoundException {
        logger.log("Retrieving contractor by ID from memory repository : " + entityId);

        final Contractor contractor = data.get(Objects.requireNonNull(entityId));
        if (contractor == null) {
            throw new UserNotFoundException("No contractor for " + entityId.value());
        }
        return contractor;
    }

    @Override
    public List<Contractor> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public void remove(Contractor item) {
        data.remove(item.entityId());
    }

    @Override
    public EntityId nextId() {
        return EntityId.of(String.valueOf(counter.incrementAndGet()));
    }
}
