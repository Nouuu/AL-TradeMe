package org.larrieulacoste.noe.al.trademe.features.invoices.application.query;

import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.kernel.query.Query;

public final class RetrieveInvoiceById implements Query {
    public final EntityId invoiceId;

    public RetrieveInvoiceById(EntityId invoiceId) {
        this.invoiceId = invoiceId;
    }

}
