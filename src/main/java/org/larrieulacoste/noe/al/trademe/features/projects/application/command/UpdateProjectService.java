package org.larrieulacoste.noe.al.trademe.features.projects.application.command;

import org.larrieulacoste.noe.al.trademe.domain.event.ProjectUpdated;
import org.larrieulacoste.noe.al.trademe.features.projects.domain.Project;
import org.larrieulacoste.noe.al.trademe.features.projects.domain.ProjectBuilder;
import org.larrieulacoste.noe.al.trademe.features.projects.domain.ProjectValidationService;
import org.larrieulacoste.noe.al.trademe.features.projects.domain.Projects;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.EntityId;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UpdateProjectService implements CommandHandler<UpdateProject, Void> {
    private final Projects projects;
    private final ProjectValidationService projectValidationService;
    private final EventBus<ApplicationEvent> eventBus;
    private final ProjectBuilder projectBuilder;

    public UpdateProjectService(Projects projects, ProjectValidationService projectValidationService,
            EventBus<ApplicationEvent> eventBus, ProjectBuilder projectBuilder) {
        this.projects = projects;
        this.projectValidationService = projectValidationService;
        this.eventBus = eventBus;
        this.projectBuilder = projectBuilder;
    }

    @Override
    public Void handle(UpdateProject updateProject) {
        Project inMemoryProject = projects.byId(EntityId.of(updateProject.projectId()));

        projectValidationService.validateUpdateProject(updateProject);

        projectBuilder.clear();
        projectBuilder.withProject(inMemoryProject);

        if (updateProject.taskName() != null) {
            projectBuilder.withTaskName(updateProject.taskName());
        }
        if (updateProject.dailyRate() != null) {
            projectBuilder.withDailyRate(updateProject.dailyRate());
        }
        if (updateProject.locationName() != null || updateProject.latitude() != null
                || updateProject.longitude() != null) {
            projectBuilder.withLocation(updateProject.locationName(), updateProject.latitude(),
                    updateProject.longitude());
        }

        Project updatedProject = projectBuilder.build(inMemoryProject.projectId());
        projects.save(updatedProject);

        eventBus.publish(
                ProjectUpdated.of(
                        updatedProject.projectId(),
                        updatedProject.taskName().value(),
                        updatedProject.contractorId(),
                        updatedProject.period().startDate(),
                        updatedProject.period().endDate(),
                        updatedProject.dailyRate().amount().value(),
                        updatedProject.location().locationName().value()));

        return null;
    }
}
