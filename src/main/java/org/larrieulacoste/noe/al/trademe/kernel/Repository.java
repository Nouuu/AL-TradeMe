package org.larrieulacoste.noe.al.trademe.kernel;

import org.larrieulacoste.noe.al.trademe.domain.exception.NotFoundException;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;

import java.util.List;
import java.util.UUID;

public interface Repository<T> {

    void save(T item);

    T byId(EntityId entityId) throws NotFoundException;

    List<T> findAll();

    void remove(T item);

    default EntityId nextId() {
        return EntityId.fromUUID(UUID.randomUUID());
    }

}
