package org.larrieulacoste.noe.al.trademe.features.projects.application.command;

import javax.enterprise.context.ApplicationScoped;
import org.larrieulacoste.noe.al.trademe.domain.event.TradesmanAssigned;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.EntityId;
import org.larrieulacoste.noe.al.trademe.features.projects.domain.Project;
import org.larrieulacoste.noe.al.trademe.features.projects.domain.ProjectBuilder;
import org.larrieulacoste.noe.al.trademe.features.projects.domain.Projects;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;

@ApplicationScoped
public class AssignTradesmanService implements CommandHandler<AssignTradesman, Project> {
    private final Projects projects;
    private final ProjectBuilder projectBuilder;
    private final EventBus<ApplicationEvent> eventBus;

    public AssignTradesmanService(Projects projects, ProjectBuilder projectBuilder, EventBus<ApplicationEvent> eventBus) {
        this.projects = projects;
        this.projectBuilder = projectBuilder;
        this.eventBus = eventBus;
    }

    @Override
    public Project handle(AssignTradesman command) {
        EntityId projectId = EntityId.of(command.projectId());
        EntityId tradesmanId = EntityId.of(command.tradesmanId());
        Project oldProject = projects.byId(projectId);

        projectBuilder.clear();
        Project newProject = projectBuilder.withProject(oldProject)
                .addTradesmanId(tradesmanId)
                .build(oldProject.projectId());

        projects.save(newProject);

        eventBus.publish(TradesmanAssigned.of(projectId, tradesmanId, oldProject.period().startDate(), oldProject.period().endDate()));
        return newProject;
    }
}
