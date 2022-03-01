package org.larrieulacoste.noe.al.trademe.features.projects.application.command;

import org.larrieulacoste.noe.al.trademe.domain.event.ProjectRequiredSkillAdded;
import org.larrieulacoste.noe.al.trademe.features.members.domain.NotEmptyString;
import org.larrieulacoste.noe.al.trademe.features.projects.domain.Project;
import org.larrieulacoste.noe.al.trademe.features.projects.domain.ProjectBuilder;
import org.larrieulacoste.noe.al.trademe.features.projects.domain.ProjectValidationService;
import org.larrieulacoste.noe.al.trademe.features.projects.domain.Projects;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;
import org.larrieulacoste.noe.al.trademe.kernel.validators.StringValidators;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.EntityId;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.Skill;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.SkillRequest;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class AddProjectRequiredSkillService implements CommandHandler<AddProjectRequiredSkill, List<SkillRequest>> {
    private final Projects projects;
    private final ProjectValidationService projectValidationService;
    private final StringValidators stringValidators;
    private final EventBus<ApplicationEvent> eventBus;
    private final ProjectBuilder projectBuilder;

    public AddProjectRequiredSkillService(Projects projects, ProjectValidationService projectValidationService,
                                          StringValidators stringValidators, EventBus<ApplicationEvent> eventBus, ProjectBuilder projectBuilder) {
        this.projects = projects;
        this.projectValidationService = projectValidationService;
        this.stringValidators = stringValidators;
        this.eventBus = eventBus;
        this.projectBuilder = projectBuilder;
    }

    @Override
    public List<SkillRequest> handle(AddProjectRequiredSkill requiredSkill) {
        Project inMemoryProject = projects.byId(EntityId.of(requiredSkill.projectId()));

        projectValidationService.validateAddOrRemoveProjectRequiredSkill(requiredSkill.requiredSkill());

        Skill newSkill = Skill.of(
                NotEmptyString.of(requiredSkill.requiredSkill().skillName(), stringValidators),
                requiredSkill.requiredSkill().requiredLevel()
        );
        List<Skill> projectRequiredSkills = new ArrayList<>(inMemoryProject.requiredSkills());

        projectRequiredSkills.stream()
                .filter(skill -> skill.skillName().equals(newSkill.skillName()))
                .findFirst().ifPresent(projectRequiredSkills::remove);
        projectRequiredSkills.add(newSkill);

        projectBuilder.clear();
        projectBuilder.withProject(inMemoryProject);
        projectBuilder.withRequiredSkills(projectRequiredSkills);

        Project project = projectBuilder.build(inMemoryProject.projectId());
        projects.save(project);

        eventBus.publish(
                ProjectRequiredSkillAdded.of(
                        project.projectId(),
                        newSkill.skillName().value(),
                        newSkill.requiredLevel()
                )
        );

        return projectRequiredSkills.stream().map(currentRequiredSkill ->
                        new SkillRequest(currentRequiredSkill.skillName().value(), currentRequiredSkill.requiredLevel()))
                .toList();
    }
}
