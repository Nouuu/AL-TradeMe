package org.larrieulacoste.noe.al.trademe.features.projects.application.command;

import javax.enterprise.context.ApplicationScoped;
import org.larrieulacoste.noe.al.trademe.application.event.TradesmanTerminated;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.features.projects.domain.Project;
import org.larrieulacoste.noe.al.trademe.features.projects.domain.ProjectBuilder;
import org.larrieulacoste.noe.al.trademe.features.projects.domain.Projects;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;

@ApplicationScoped
public class TerminateTradesmanService implements CommandHandler<TerminateTradesman, Project> {
    private final Projects projects;
    private final ProjectBuilder projectBuilder;
    private final EventBus<ApplicationEvent> eventBus;

    public TerminateTradesmanService(Projects projects, ProjectBuilder projectBuilder, EventBus<ApplicationEvent> eventBus) {
        this.projects = projects;
        this.projectBuilder = projectBuilder;
        this.eventBus = eventBus;
    }

    @Override
    public Project handle(TerminateTradesman command) {
        EntityId projectId = EntityId.of(command.projectId());
        EntityId removedTradesmanId = EntityId.of(command.tradesmanId());

        Project oldProject = projects.byId(projectId);

        projectBuilder.clear();
        projectBuilder.withProject(oldProject);
        projectBuilder.withTradesmenEntityIds(oldProject.tradesmenIds().stream()
                .filter(tradesmanId -> tradesmanId != removedTradesmanId)
                .toList());

        Project newProject = projectBuilder.build(oldProject.projectId());
        projects.save(newProject);

        eventBus.publish(TradesmanTerminated.of(
                projectId,
                removedTradesmanId,
                oldProject.period().startDate(),
                oldProject.period().endDate())
        );

        return newProject;
    }
}
