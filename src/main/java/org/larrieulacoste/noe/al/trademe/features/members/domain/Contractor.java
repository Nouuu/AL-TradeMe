package org.larrieulacoste.noe.al.trademe.features.members.domain;

import org.larrieulacoste.noe.al.trademe.domain.model.EmailAddress;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.domain.model.NotEmptyString;
import org.larrieulacoste.noe.al.trademe.domain.model.Password;

public final class Contractor extends User {

    private Contractor(EntityId entityId, NotEmptyString lastname, NotEmptyString firstname, EmailAddress email, Password password) {
        super(entityId, lastname, firstname, email, password);
    }

    public static Contractor of(EntityId entityId, NotEmptyString lastname, NotEmptyString firstname, EmailAddress email, Password password) {
        return new Contractor(entityId, lastname, firstname, email, password);
    }

    public static Contractor withUser(User user) {
        return new Contractor(user.getUserId(), user.getLastname(), user.getFirstname(), user.getEmail(), user.getPassword());
    }
}
