package org.larrieulacoste.noe.al.trademe.domain.entity;

import org.larrieulacoste.noe.al.trademe.domain.model.EmailAddress;
import org.larrieulacoste.noe.al.trademe.domain.model.NotEmptyString;
import org.larrieulacoste.noe.al.trademe.domain.model.Password;
import org.larrieulacoste.noe.al.trademe.domain.model.UserId;

public final class Contractor extends User {

    private Contractor(UserId userId, NotEmptyString lastname, NotEmptyString firstname, EmailAddress email, Password password) {
        super(userId, lastname, firstname, email, password);
    }

    public static Contractor of(UserId userId, NotEmptyString lastname, NotEmptyString firstname, EmailAddress email, Password password) {
        return new Contractor(userId, lastname, firstname, email, password);
    }
}
