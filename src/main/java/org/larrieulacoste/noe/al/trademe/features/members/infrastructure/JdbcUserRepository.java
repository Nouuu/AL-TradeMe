package org.larrieulacoste.noe.al.trademe.features.members.infrastructure;

import org.larrieulacoste.noe.al.trademe.domain.Repository;
import org.larrieulacoste.noe.al.trademe.domain.entity.User;
import org.larrieulacoste.noe.al.trademe.domain.logger.Logger;
import org.larrieulacoste.noe.al.trademe.domain.logger.LoggerFactory;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;

import java.util.Objects;

public final class JdbcUserRepository implements Repository<User> {
    private static final String NOT_YET_IMPLEMENTED_MESSAGE = "Not yet implemented";
    private final Logger logger;

    public JdbcUserRepository(LoggerFactory loggerFactory) {
        this.logger = Objects.requireNonNull(loggerFactory).getLogger(this);
        this.logger.error("Warning !!! Your are instancing a not yet implemented repository");
    }

    @Override
    public void save(User user) {
        throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED_MESSAGE);
    }

    @Override
    public User byId(EntityId entityId) {
        throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED_MESSAGE);
    }

    @Override
    public EntityId nextId() {
        throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED_MESSAGE);
    }
}
