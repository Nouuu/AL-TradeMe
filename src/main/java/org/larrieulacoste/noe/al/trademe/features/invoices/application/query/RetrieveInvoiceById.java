package org.larrieulacoste.noe.al.trademe.features.invoices.application.query;

import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.kernel.query.Query;

public record RetrieveInvoiceById(EntityId invoiceId) implements Query {

}
