package org.larrieulacoste.noe.al.trademe.features.projects.application.query;

import org.larrieulacoste.noe.al.trademe.features.projects.domain.Project;
import org.larrieulacoste.noe.al.trademe.features.projects.domain.Projects;
import org.larrieulacoste.noe.al.trademe.kernel.query.QueryHandler;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.EntityId;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RetrieveProjectByIdService implements QueryHandler<RetrieveProjectById, Project> {
    private final Projects projects;

    public RetrieveProjectByIdService(Projects projects) {
        this.projects = projects;
    }


    @Override
    public Project handle(RetrieveProjectById query) {
        return projects.byId(EntityId.of(query.projectId()));
    }
}
