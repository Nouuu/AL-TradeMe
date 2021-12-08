package org.larrieulacoste.noe.al.trademe.features.members.domain;

import org.larrieulacoste.noe.al.trademe.domain.entity.User;
import org.larrieulacoste.noe.al.trademe.domain.model.UserId;
import org.larrieulacoste.noe.al.trademe.application.exception.UserNotFoundException;

import java.util.UUID;

public interface UserRepository {

    void save(User user);

    User byId(UserId userId) throws UserNotFoundException;

    default UserId nextId() {
        return UserId.fromUUID(UUID.randomUUID());
    }
}
