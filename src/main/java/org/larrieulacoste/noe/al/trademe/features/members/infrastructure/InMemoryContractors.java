package org.larrieulacoste.noe.al.trademe.features.members.infrastructure;

import org.larrieulacoste.noe.al.trademe.features.members.domain.Contractor;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Contractors;
import org.larrieulacoste.noe.al.trademe.kernel.exception.UserNotFoundException;
import org.larrieulacoste.noe.al.trademe.kernel.logger.Logger;
import org.larrieulacoste.noe.al.trademe.kernel.serializer.SerializationEngine;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.EntityId;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public final class InMemoryContractors implements Contractors {
    private final AtomicInteger counter = new AtomicInteger(0);
    private final Map<EntityId, Contractor> data = new ConcurrentHashMap<>();
    private final SerializationEngine<List<Contractor>> contractorsSerializer;
    private final Logger logger;

    public InMemoryContractors(SerializationEngine<List<Contractor>> contractorsSerializer, Logger logger) {
        this.contractorsSerializer = contractorsSerializer;
        this.logger = logger;
    }

    @Override
    public void save(Contractor contractor) {
        logger.log("Saving contractor in memory repository : " + contractor);

        data.put(Objects.requireNonNull(contractor).entityId(), contractor);
        saveJson();
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
        saveJson();
    }

    @Override
    public EntityId nextId() {
        return EntityId.of(String.valueOf(counter.incrementAndGet()));
    }

    private void saveJson() {
        String jsonContractors = this.contractorsSerializer.apply(data.values().stream().toList());
        System.out.println(jsonContractors);
    }
}
