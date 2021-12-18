package org.larrieulacoste.noe.al.trademe.features.members.infrastructure;

import org.larrieulacoste.noe.al.trademe.application.exception.UserNotFoundException;
import org.larrieulacoste.noe.al.trademe.domain.logger.Logger;
import org.larrieulacoste.noe.al.trademe.domain.logger.LoggerFactory;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.features.members.domain.MemberRepository;
import org.larrieulacoste.noe.al.trademe.features.members.domain.User;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public final class InMemoryMemberRepository implements MemberRepository {
    private final AtomicInteger counter = new AtomicInteger(0);
    private final Map<EntityId, User> data = new ConcurrentHashMap<>();
    private final Logger logger;

    public InMemoryMemberRepository(LoggerFactory loggerFactory) {
        this.logger = Objects.requireNonNull(loggerFactory).getLogger(this);
    }

    @Override
    public void save(User user) {
        logger.log("Saving user in memory repository : " + user);

        data.put(Objects.requireNonNull(user).getUserId(), user);
    }

    @Override
    public User byId(EntityId entityId) throws UserNotFoundException {
        logger.log("Retrieving user by ID from memory repository : " + entityId);

        final User user = data.get(Objects.requireNonNull(entityId));
        if (user == null) {
            throw new UserNotFoundException("No user for " + entityId.getValue());
        }
        return user;
    }

    @Override
    public EntityId nextId() {
        return EntityId.of(String.valueOf(counter.incrementAndGet()));
    }
}
