package org.larrieulacoste.noe.al.trademe.features.members.application;

import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.features.members.infrastructure.kernel.query.Query;

public final class RetrieveContractorById implements Query {
    public final EntityId contractorId;

    public RetrieveContractorById(EntityId contractorId) {
        this.contractorId = contractorId;
    }

}
