package org.larrieulacoste.noe.al.trademe.features.projects.application.query;

import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.features.projects.domain.Project;
import org.larrieulacoste.noe.al.trademe.features.projects.domain.Projects;
import org.larrieulacoste.noe.al.trademe.kernel.query.QueryHandler;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class RetrieveContractorProjectsService implements QueryHandler<RetrieveContractorProjects, List<Project>> {
    private final Projects projects;

    public RetrieveContractorProjectsService(Projects projects) {
        this.projects = projects;
    }


    @Override
    public List<Project> handle(RetrieveContractorProjects query) {
        return projects.getContractorProjects(EntityId.of(query.contractorId()));
    }
}
