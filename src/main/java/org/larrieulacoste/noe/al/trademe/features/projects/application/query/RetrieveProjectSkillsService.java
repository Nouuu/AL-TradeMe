package org.larrieulacoste.noe.al.trademe.features.projects.application.query;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.domain.model.SkillRequest;
import org.larrieulacoste.noe.al.trademe.features.projects.domain.Project;
import org.larrieulacoste.noe.al.trademe.features.projects.domain.Projects;
import org.larrieulacoste.noe.al.trademe.kernel.query.QueryHandler;

@ApplicationScoped
public class RetrieveProjectSkillsService implements QueryHandler<RetrieveProjectSkills, List<SkillRequest>> {
    private final Projects projects;

    public RetrieveProjectSkillsService(Projects projects) {
        this.projects = projects;
    }

    @Override
    public List<SkillRequest> handle(RetrieveProjectSkills command) {
        Project project = projects.byId(EntityId.of(command.projectId()));

        return project.requiredSkills().stream().map(currentRequiredSkill ->
                        new SkillRequest(currentRequiredSkill.skillName().value(), currentRequiredSkill.requiredLevel()))
                .toList();
    }
}
