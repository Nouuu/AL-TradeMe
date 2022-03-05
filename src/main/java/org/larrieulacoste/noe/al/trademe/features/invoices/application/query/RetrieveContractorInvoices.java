package org.larrieulacoste.noe.al.trademe.features.invoices.application.query;

import org.larrieulacoste.noe.al.trademe.kernel.query.Query;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.EntityId;

public record RetrieveContractorInvoices(EntityId contractorId) implements Query {
}
