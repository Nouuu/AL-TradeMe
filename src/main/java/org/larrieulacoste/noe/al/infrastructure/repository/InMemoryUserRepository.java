package org.larrieulacoste.noe.al.infrastructure.repository;

import org.larrieulacoste.noe.al.domain.entity.User;
import org.larrieulacoste.noe.al.domain.entity.UserId;
import org.larrieulacoste.noe.al.domain.exception.UserNotFoundException;
import org.larrieulacoste.noe.al.domain.logger.Logger;
import org.larrieulacoste.noe.al.domain.logger.LoggerFactory;
import org.larrieulacoste.noe.al.domain.repository.UserRepository;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryUserRepository implements UserRepository {
    private final AtomicInteger counter = new AtomicInteger(0);
    private final Map<UserId, User> data = new ConcurrentHashMap<>();
    private final Logger logger;

    public InMemoryUserRepository(LoggerFactory loggerFactory) {
        this.logger = Objects.requireNonNull(loggerFactory).getLogger(this);
    }

    @Override
    public void save(User user) {
        logger.log("Saving user in memory repository : " + user);

        data.put(Objects.requireNonNull(user).getUserId(), user);
    }

    @Override
    public User byId(UserId userId) throws UserNotFoundException {
        logger.log("Retrieving user by ID from memory repository : " + userId);

        final User user = data.get(Objects.requireNonNull(userId));
        if (user == null) {
            throw new UserNotFoundException("No user for " + userId.getValue());
        }
        return user;
    }

    @Override
    public UserId nextId() {
        return UserId.of(String.valueOf(counter.incrementAndGet()));
    }
}
