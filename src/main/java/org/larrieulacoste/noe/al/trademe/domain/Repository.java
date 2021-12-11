package org.larrieulacoste.noe.al.trademe.domain;

import org.larrieulacoste.noe.al.trademe.application.exception.NotFoundException;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;

import java.util.UUID;

public interface Repository<T> {

    void save(T item);

    T byId(EntityId entityId) throws NotFoundException;

    default EntityId nextId() {
        return EntityId.fromUUID(UUID.randomUUID());
    }

}
