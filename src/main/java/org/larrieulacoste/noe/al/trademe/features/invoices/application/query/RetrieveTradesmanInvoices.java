package org.larrieulacoste.noe.al.trademe.features.invoices.application.query;

import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.kernel.query.Query;

public final class RetrieveTradesmanInvoices implements Query {
    public final EntityId tradesmanId;

    public RetrieveTradesmanInvoices(EntityId tradesmanId) {
        this.tradesmanId = tradesmanId;
    }
}
