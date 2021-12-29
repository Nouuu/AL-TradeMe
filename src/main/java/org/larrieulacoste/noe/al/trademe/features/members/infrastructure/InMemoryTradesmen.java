package org.larrieulacoste.noe.al.trademe.features.members.infrastructure;

import org.larrieulacoste.noe.al.trademe.application.exception.UserNotFoundException;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesman;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesmen;
import org.larrieulacoste.noe.al.trademe.kernel.logger.Logger;
import org.larrieulacoste.noe.al.trademe.kernel.logger.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public final class InMemoryTradesmen implements Tradesmen {
    private final AtomicInteger counter = new AtomicInteger(0);
    private final Map<EntityId, Tradesman> data = new ConcurrentHashMap<>();
    private final Logger logger;

    public InMemoryTradesmen() {
        this.logger = LoggerFactory.getLogger(this);
    }

    @Override
    public void save(Tradesman tradesman) {
        logger.log("Saving tradesman in memory repository : " + tradesman);

        data.put(Objects.requireNonNull(tradesman).getEntityId(), tradesman);
    }

    @Override
    public Tradesman byId(EntityId entityId) throws UserNotFoundException {
        logger.log("Retrieving tradesman by ID from memory repository : " + entityId);

        final Tradesman tradesman = data.get(Objects.requireNonNull(entityId));
        if (tradesman == null) {
            throw new UserNotFoundException("No tradesman for " + entityId.getValue());
        }
        return tradesman;
    }

    @Override
    public List<Tradesman> findAll() {
        return List.copyOf(data.values());
    }

    @Override
    public EntityId nextId() {
        return EntityId.of(String.valueOf(counter.incrementAndGet()));
    }
}
