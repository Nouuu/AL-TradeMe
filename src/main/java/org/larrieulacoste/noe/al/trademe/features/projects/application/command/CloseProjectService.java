package org.larrieulacoste.noe.al.trademe.features.projects.application.command;

import java.time.ZonedDateTime;
import javax.enterprise.context.ApplicationScoped;
import org.larrieulacoste.noe.al.trademe.application.event.ProjectClosed;
import org.larrieulacoste.noe.al.trademe.application.event.TradesmanTerminated;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.features.projects.domain.Project;
import org.larrieulacoste.noe.al.trademe.features.projects.domain.ProjectBuilder;
import org.larrieulacoste.noe.al.trademe.features.projects.domain.Projects;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;

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
        ZonedDateTime now = ZonedDateTime.now();
        if (inMemoryProject.period().startDate().isBefore(now) && inMemoryProject.period().endDate().isAfter(now)) {
            projectBuilder.withPeriod(
                    inMemoryProject.period().startDate(),
                    now
            );
        }
        Project project = projectBuilder.build(inMemoryProject.projectId());
        projects.save(project);

        inMemoryProject.tradesmenIds().forEach(tradesmanId -> eventBus.publish(TradesmanTerminated.of(
                inMemoryProject.projectId(),
                tradesmanId,
                inMemoryProject.period().startDate(),
                inMemoryProject.period().endDate()))
        );

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
