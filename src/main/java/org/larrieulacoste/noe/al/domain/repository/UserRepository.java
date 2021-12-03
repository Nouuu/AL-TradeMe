package org.larrieulacoste.noe.al.domain.repository;

import org.larrieulacoste.noe.al.domain.entity.User;
import org.larrieulacoste.noe.al.domain.entity.UserId;
import org.larrieulacoste.noe.al.domain.exception.UserNotFoundException;

import java.util.UUID;

public interface UserRepository {

    void save(User user);

    User byId(UserId userId) throws UserNotFoundException;

    default UserId nextId() {
        return UserId.fromUUID(UUID.randomUUID());
    }
}
