package org.larrieulacoste.noe.al.trademe.features.projects.application.query;

import org.larrieulacoste.noe.al.trademe.features.projects.domain.Project;
import org.larrieulacoste.noe.al.trademe.features.projects.domain.Projects;
import org.larrieulacoste.noe.al.trademe.kernel.query.QueryHandler;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class RetrieveProjectsService implements QueryHandler<RetrieveProjects, List<Project>> {
    private final Projects projects;

    public RetrieveProjectsService(Projects projects) {
        this.projects = projects;
    }


    @Override
    public List<Project> handle(RetrieveProjects query) {
        return projects.findAll();
    }
}
