package org.larrieulacoste.noe.al.trademe.kernel;

import org.larrieulacoste.noe.al.trademe.kernel.exception.NotFoundException;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.EntityId;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface Repository<T> {

    void save(T item) throws IOException;

    T byId(EntityId entityId) throws NotFoundException;

    List<T> findAll();

    void remove(T item) throws IOException;

    default EntityId nextId() {
        return EntityId.fromUUID(UUID.randomUUID());
    }

}
