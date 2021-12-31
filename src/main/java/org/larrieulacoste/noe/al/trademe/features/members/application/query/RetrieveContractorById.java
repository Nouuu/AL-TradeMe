package org.larrieulacoste.noe.al.trademe.features.members.application.query;

import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.kernel.query.Query;

public final class RetrieveContractorById implements Query {
    public final EntityId contractorId;

    public RetrieveContractorById(EntityId contractorId) {
        this.contractorId = contractorId;
    }

}
