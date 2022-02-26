package org.larrieulacoste.noe.al.trademe.features.projects.application.query;

import org.larrieulacoste.noe.al.trademe.kernel.query.Query;

public record RetrieveTradesmanProjects(
        String projectId,
        String tradesmanId
) implements Query {
}
