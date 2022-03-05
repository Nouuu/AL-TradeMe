package org.larrieulacoste.noe.al.trademe.features.members.application.query;

import org.larrieulacoste.noe.al.trademe.kernel.query.Query;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.EntityId;

public record RetrieveContractorById(EntityId contractorId) implements Query {
}
