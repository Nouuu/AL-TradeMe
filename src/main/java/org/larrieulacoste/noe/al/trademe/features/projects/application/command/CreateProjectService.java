package org.larrieulacoste.noe.al.trademe.features.projects.application.command;

import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.features.projects.domain.Project;
import org.larrieulacoste.noe.al.trademe.features.projects.domain.ProjectBuilder;
import org.larrieulacoste.noe.al.trademe.features.projects.domain.ProjectValidationService;
import org.larrieulacoste.noe.al.trademe.features.projects.domain.Projects;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CreateProjectService implements CommandHandler<CreateProject, EntityId> {
    private final Projects projects;
    private final ProjectValidationService projectValidationService;
    private final EventBus<ApplicationEvent> eventBus;
    private final ProjectBuilder projectBuilder;

    public CreateProjectService(Projects projects, ProjectValidationService projectValidationService,
                                EventBus<ApplicationEvent> eventBus, ProjectBuilder projectBuilder) {
        this.projects = projects;
        this.projectValidationService = projectValidationService;
        this.eventBus = eventBus;
        this.projectBuilder = projectBuilder;
    }

    @Override
    public EntityId handle(CreateProject createProject) {
        projectValidationService.validateCreateProject(createProject);

        final EntityId projectId = projects.nextId();
        projectBuilder.clear();
        projectBuilder.
                withTaskName(createProject.taskName())
                .withRequiredSkills(createProject.skills())
                .withProfessions(createProject.professions())
                .withContractorId(createProject.contractorId())
                .withPeriod(createProject.startDate(), createProject.endDate())
                .withDailyRate(createProject.dailyRate())
                .withLocation(createProject.locationName(), createProject.latitude(), createProject.longitude());

        Project project = projectBuilder.build(projectId);
        projects.save(project);

        // TODO publish event

        return projectId;
    }
}
