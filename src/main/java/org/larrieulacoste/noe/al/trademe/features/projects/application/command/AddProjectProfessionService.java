package org.larrieulacoste.noe.al.trademe.features.projects.application.command;

import org.larrieulacoste.noe.al.trademe.domain.event.ProjectProfessionAdded;
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
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.Profession;

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
        projectBuilder.withProfessions(projectProfessions);

        Project project = projectBuilder.build(inMemoryProject.projectId());
        projects.save(project);

        eventBus.publish(
                ProjectProfessionAdded.of(
                        project.projectId(),
                        newProfession.professionName().value()
                )
        );

        return projectProfessions.stream().map(currentProfession -> currentProfession.professionName().value()).toList();
    }
}
