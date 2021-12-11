package org.larrieulacoste.noe.al.trademe.features.members.domain;

import org.larrieulacoste.noe.al.trademe.domain.Repository;
import org.larrieulacoste.noe.al.trademe.domain.entity.User;

public interface UserRepositoryFactory {
    Repository<User> getRepository();
}
