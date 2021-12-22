package org.larrieulacoste.noe.al.trademe.features.members.application;

import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.kernel.query.Query;

public final class RetrieveTradesmanById implements Query {
    public final EntityId tradesmanId;

    public RetrieveTradesmanById(EntityId tradesmanId) {
        this.tradesmanId = tradesmanId;
    }

}
