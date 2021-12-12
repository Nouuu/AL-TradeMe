package org.larrieulacoste.noe.al.trademe.domain.entity;

import org.larrieulacoste.noe.al.trademe.domain.model.EmailAddress;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.domain.model.NotEmptyString;
import org.larrieulacoste.noe.al.trademe.domain.model.Password;

public final class Tradesman extends User {

    private Tradesman(EntityId entityId, NotEmptyString lastname, NotEmptyString firstname, EmailAddress email, Password password) {
        super(entityId, lastname, firstname, email, password);
    }

    public static Tradesman of(EntityId entityId, NotEmptyString lastname, NotEmptyString firstname, EmailAddress email, Password password) {
        return new Tradesman(entityId, lastname, firstname, email, password);
    }
}
