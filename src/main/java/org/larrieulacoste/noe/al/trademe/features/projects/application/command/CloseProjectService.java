package org.larrieulacoste.noe.al.trademe.features.projects.application.command;

import org.larrieulacoste.noe.al.trademe.application.event.ProjectClosed;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.features.projects.domain.Project;
import org.larrieulacoste.noe.al.trademe.features.projects.domain.ProjectBuilder;
import org.larrieulacoste.noe.al.trademe.features.projects.domain.Projects;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;

import javax.enterprise.context.ApplicationScoped;
import java.time.ZonedDateTime;

@ApplicationScoped
public class CloseProjectService implements CommandHandler<CloseProject, Void> {
    private final Projects projects;
    private final EventBus<ApplicationEvent> eventBus;
    private final ProjectBuilder projectBuilder;

    public CloseProjectService(Projects projects, EventBus<ApplicationEvent> eventBus, ProjectBuilder projectBuilder) {
        this.projects = projects;
        this.eventBus = eventBus;
        this.projectBuilder = projectBuilder;
    }

    @Override
    public Void handle(CloseProject closeProject) {

        Project inMemoryProject = projects.byId(EntityId.of(closeProject.projectId()));


        projectBuilder.clear();
        projectBuilder.withProject(inMemoryProject);
        projectBuilder.withPeriod(
                inMemoryProject.period().startDate(), // TODO Problem
                ZonedDateTime.now() // Problem
        );

        Project project = projectBuilder.build(inMemoryProject.projectId());
        projects.save(project);

        // TODO release tradesmen with old period

        eventBus.publish(
                ProjectClosed.of(
                        project.projectId(),
                        project.period().startDate(),
                        project.period().endDate()
                )
        );

        return null;
    }
}
