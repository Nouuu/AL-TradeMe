package org.larrieulacoste.noe.al.trademe.features.projects.application.query;

import org.larrieulacoste.noe.al.trademe.features.projects.domain.Project;
import org.larrieulacoste.noe.al.trademe.features.projects.domain.Projects;
import org.larrieulacoste.noe.al.trademe.kernel.query.QueryHandler;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.EntityId;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class RetrieveTradesmanProjectsService implements QueryHandler<RetrieveTradesmanProjects, List<Project>> {
    private final Projects projects;

    public RetrieveTradesmanProjectsService(Projects projects) {
        this.projects = projects;
    }


    @Override
    public List<Project> handle(RetrieveTradesmanProjects query) {
        return projects.getTradesmanProjects(EntityId.of(query.tradesmanId()));
    }
}
