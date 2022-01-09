package org.larrieulacoste.noe.al.trademe.features.invoices.application.query;

import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.kernel.query.Query;

public final class RetrieveContractorInvoices implements Query {
    public final EntityId contractorId;

    public RetrieveContractorInvoices(EntityId contractorId) {
        this.contractorId = contractorId;
    }
}
