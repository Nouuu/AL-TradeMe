package org.larrieulacoste.noe.al.trademe.features.projects.application.command;

import org.larrieulacoste.noe.al.trademe.application.event.ProjectCreated;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.domain.model.Profession;
import org.larrieulacoste.noe.al.trademe.features.members.domain.NotEmptyString;
import org.larrieulacoste.noe.al.trademe.features.projects.domain.Project;
import org.larrieulacoste.noe.al.trademe.features.projects.domain.ProjectBuilder;
import org.larrieulacoste.noe.al.trademe.features.projects.domain.ProjectValidationService;
import org.larrieulacoste.noe.al.trademe.features.projects.domain.Projects;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;
import org.larrieulacoste.noe.al.trademe.kernel.validators.StringValidators;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class AddProjectProfessionService implements CommandHandler<AddProjectProfession, List<String>> {
    private final Projects projects;
    private final ProjectValidationService projectValidationService;
    private final StringValidators stringValidators;
    private final EventBus<ApplicationEvent> eventBus;
    private final ProjectBuilder projectBuilder;

    public AddProjectProfessionService(Projects projects, ProjectValidationService projectValidationService,
                                       StringValidators stringValidators, EventBus<ApplicationEvent> eventBus, ProjectBuilder projectBuilder) {
        this.projects = projects;
        this.projectValidationService = projectValidationService;
        this.stringValidators = stringValidators;
        this.eventBus = eventBus;
        this.projectBuilder = projectBuilder;
    }

    @Override
    public List<String> handle(AddProjectProfession profession) {
        Project inMemoryProject = projects.byId(EntityId.of(profession.projectId()));

        projectValidationService.validateAddProjectProfession(profession);

        Profession newProfession = Profession.of(NotEmptyString.of(profession.profession(), stringValidators));
        List<Profession> projectProfessions = new ArrayList<>(inMemoryProject.professions());
        if (!projectProfessions.contains(newProfession)) {
            projectProfessions.add(newProfession);
        }

        projectBuilder.clear();
        projectBuilder.withProject(inMemoryProject);
        projectBuilder.withProfessions(projectProfessions)

        Project project = projectBuilder.build(projectId);
        projects.save(project);

        eventBus.publish(
                ProjectCreated.of(
                        projectId,
                        project.taskName().value(),
                        project.contractorId(),
                        project.period().startDate(),
                        project.period().endDate(),
                        project.dailyRate().amount().value(),
                        project.location().locationName().value()
                )
        );

        return projectId;
    }
}
