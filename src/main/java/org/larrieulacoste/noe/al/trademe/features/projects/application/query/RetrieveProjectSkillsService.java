package org.larrieulacoste.noe.al.trademe.features.projects.application.query;

import org.larrieulacoste.noe.al.trademe.features.projects.domain.Project;
import org.larrieulacoste.noe.al.trademe.features.projects.domain.Projects;
import org.larrieulacoste.noe.al.trademe.kernel.query.QueryHandler;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.EntityId;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.Skill;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class RetrieveProjectSkillsService implements QueryHandler<RetrieveProjectSkills, List<Skill>> {
    private final Projects projects;

    public RetrieveProjectSkillsService(Projects projects) {
        this.projects = projects;
    }

    @Override
    public List<Skill> handle(RetrieveProjectSkills command) {
        Project project = projects.byId(EntityId.of(command.projectId()));

        return project.requiredSkills();
    }
}
