package org.larrieulacoste.noe.al.trademe.features.members.infrastructure;

import org.larrieulacoste.noe.al.trademe.application.exception.UserNotFoundException;
import org.larrieulacoste.noe.al.trademe.domain.logger.Logger;
import org.larrieulacoste.noe.al.trademe.domain.logger.LoggerFactory;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Contractor;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Contractors;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public final class InMemoryContractors implements Contractors {
    private final AtomicInteger counter = new AtomicInteger(0);
    private final Map<EntityId, Contractor> data = new ConcurrentHashMap<>();
    private final Logger logger;

    public InMemoryContractors(LoggerFactory loggerFactory) {
        this.logger = Objects.requireNonNull(loggerFactory).getLogger(this);
    }

    @Override
    public void save(Contractor contractor) {
        logger.log("Saving contractor in memory repository : " + contractor);

        data.put(Objects.requireNonNull(contractor).getEntityId(), contractor);
    }

    @Override
    public Contractor byId(EntityId entityId) throws UserNotFoundException {
        logger.log("Retrieving contractor by ID from memory repository : " + entityId);

        final Contractor contractor = data.get(Objects.requireNonNull(entityId));
        if (contractor == null) {
            throw new UserNotFoundException("No contractor for " + entityId.getValue());
        }
        return contractor;
    }

    @Override
    public List<Contractor> findAll() {
        return List.copyOf(data.values());
    }

    @Override
    public EntityId nextId() {
        return EntityId.of(String.valueOf(counter.incrementAndGet()));
    }
}