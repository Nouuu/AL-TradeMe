package org.larrieulacoste.noe.al.trademe.features.projects.application.command;

import org.larrieulacoste.noe.al.trademe.domain.event.ProjectProfessionRemoved;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.EntityId;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.Profession;
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
public class RemoveProjectProfessionService implements CommandHandler<RemoveProjectProfession, List<String>> {
    private final Projects projects;
    private final ProjectValidationService projectValidationService;
    private final StringValidators stringValidators;
    private final EventBus<ApplicationEvent> eventBus;
    private final ProjectBuilder projectBuilder;

    public RemoveProjectProfessionService(Projects projects, ProjectValidationService projectValidationService,
                                          StringValidators stringValidators, EventBus<ApplicationEvent> eventBus, ProjectBuilder projectBuilder) {
        this.projects = projects;
        this.projectValidationService = projectValidationService;
        this.stringValidators = stringValidators;
        this.eventBus = eventBus;
        this.projectBuilder = projectBuilder;
    }

    @Override
    public List<String> handle(RemoveProjectProfession profession) {
        Project inMemoryProject = projects.byId(EntityId.of(profession.projectId()));

        projectValidationService.validateRemoveProjectProfession(profession);

        Profession removeProfession = Profession.of(NotEmptyString.of(profession.profession(), stringValidators));
        List<Profession> projectProfessions = new ArrayList<>(inMemoryProject.professions());

        projectProfessions.stream()
                .filter(currentProfession -> currentProfession.equals(removeProfession))
                .findFirst().ifPresent(projectProfessions::remove);

        projectBuilder.clear();
        projectBuilder.withProject(inMemoryProject);
        projectBuilder.withProfessions(projectProfessions);

        Project project = projectBuilder.build(inMemoryProject.projectId());
        projects.save(project);

        eventBus.publish(
                ProjectProfessionRemoved.of(
                        project.projectId(),
                        removeProfession.professionName().value()
                )
        );

        return projectProfessions.stream().map(currentProfession -> currentProfession.professionName().value()).toList();
    }
}
