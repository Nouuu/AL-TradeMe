package org.larrieulacoste.noe.al.trademe.features.projects.application.query;

import org.larrieulacoste.noe.al.trademe.kernel.query.Query;

public record RetrieveProjectById(
        String projectId
) implements Query {
}
