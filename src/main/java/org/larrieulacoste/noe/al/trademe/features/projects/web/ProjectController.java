package org.larrieulacoste.noe.al.trademe.features.projects.web;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.domain.model.SkillRequest;
import org.larrieulacoste.noe.al.trademe.features.projects.application.command.*;
import org.larrieulacoste.noe.al.trademe.features.projects.application.query.RetrieveContractorProjects;
import org.larrieulacoste.noe.al.trademe.features.projects.application.query.RetrieveProjectById;
import org.larrieulacoste.noe.al.trademe.features.projects.application.query.RetrieveProjects;
import org.larrieulacoste.noe.al.trademe.features.projects.application.query.RetrieveTradesmanProjects;
import org.larrieulacoste.noe.al.trademe.features.projects.domain.Project;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.query.QueryBus;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("project")
@Produces(MediaType.APPLICATION_JSON)
public final class ProjectController {
    private final QueryBus queryBus;
    private final CommandBus commandBus;

    ProjectController(QueryBus queryBus, CommandBus commandBus) {
        this.queryBus = queryBus;
        this.commandBus = commandBus;
    }

    @GET
    @Path("contractor/{contractorId}")
    @Operation(summary = "Get contractors projects", description = "Retrieve all contractors assigned projects")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<ProjectResponse> getContractorProjects(@PathParam("contractorId") String contractorId) {
        List<Project> projects = queryBus.send(new RetrieveContractorProjects(contractorId));
        return projects.stream().map(this::getProjectResponse).toList();
    }

    @GET
    @Path("tradesman/{tradesmanId}")
    @Operation(summary = "Get tradesman projects", description = "Retrieve all tradesman assigned projects")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<ProjectResponse> getTradesmanProjects(@PathParam("tradesmanId") String tradesmanId) {
        List<Project> projects = queryBus.send(new RetrieveTradesmanProjects(tradesmanId));
        return projects.stream().map(this::getProjectResponse).toList();
    }

    @GET
    @Operation(summary = "Get projects", description = "Retrieve all projects from TradeMe")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<ProjectResponse> getProjects() {
        List<Project> projects = queryBus.send(new RetrieveProjects());
        return projects.stream().map(this::getProjectResponse).toList();
    }

    @GET
    @Path("{projectId}")
    @Operation(summary = "Get project", description = "Retrieve project by id")
    @Consumes(MediaType.APPLICATION_JSON)
    public ProjectResponse getProjectById(@PathParam("projectId") String projectId) {
        Project project = queryBus.send(new RetrieveProjectById(projectId));
        return getProjectResponse(project);
    }

    @POST
    @Operation(summary = "Create project", description = "Add a new project to TradeMe")
    @Consumes(MediaType.APPLICATION_JSON)
    public EntityId createProject(ProjectRequest project) {
        return commandBus.send(new CreateProject(
                project.taskName(),
                project.skills(),
                project.professions(),
                project.contractorId(),
                project.startDate(),
                project.endDate(),
                project.dailyRate(),
                project.locationName(),
                project.longitude(),
                project.latitude()
        ));
    }

    @PUT
    @Path("{projectId}")
    @Operation(summary = "Update project", description = "Update an existing project on TradeMe")
    @Consumes(MediaType.APPLICATION_JSON)
    public EntityId updateProject(@PathParam("projectId") String projectId, UpdateProjectRequest project) {
        commandBus.send(new UpdateProject(
                projectId,
                project.taskName(),
                project.startDate(),
                project.endDate(),
                project.dailyRate(),
                project.locationName(),
                project.longitude(),
                project.latitude()
        ));

        return EntityId.of(projectId);
    }

    @POST
    @Path("{projectId}/profession")
    @Operation(summary = "Add project profession", description = "Add a new profession to a project")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<ProjectProfessionResponse> addProjectProfession(@PathParam("projectId") String projectId, ProjectProfessionRequest projectProfession) {
        List<String> updatedProfessions = commandBus
                .send(new AddProjectProfession(
                        projectId,
                        projectProfession.professionName()
                ));

        return getProjectProfessionResponses(projectId, updatedProfessions);
    }

    @POST
    @Path("{projectId}/skill")
    @Operation(summary = "Add project required skill", description = "Add or update a required skill to a project")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<ProjectSkillResponse> addProjectRequiredSkill(@PathParam("projectId") String projectId, ProjectSkillRequest projectSkill) {
        List<SkillRequest> updatedRequiredSkill = commandBus
                .send(new AddProjectRequiredSkill(
                        projectId,
                        new SkillRequest(projectSkill.skillName(), projectSkill.skillRequiredLevel())
                ));

        return getProjectSkillResponses(projectId, updatedRequiredSkill);
    }

    @PUT
    @Path("close/{projectId}")
    @Operation(summary = "Close a project", description = "Close a project in TradeMe")
    @Consumes(MediaType.APPLICATION_JSON)
    public EntityId closeProject(@PathParam("projectId") String projectId) {
        commandBus.send(new CloseProject(projectId));
        return EntityId.of(projectId);
    }

    @DELETE
    @Path("{projectId}/profession")
    @Operation(summary = "Delete a project profession", description = "Delete a profession from a project")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<ProjectProfessionResponse> deleteProjectProfession(@PathParam("projectId") String projectId, ProjectProfessionRequest projectProfession) {
        List<String> updatedProfessions = commandBus
                .send(new RemoveProjectProfession(
                        projectId,
                        projectProfession.professionName()
                ));

        return getProjectProfessionResponses(projectId, updatedProfessions);
    }

    @DELETE
    @Path("{projectId}/skill")
    @Operation(summary = "Delete a project required skill", description = "Delete a required skill from a project")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<ProjectSkillResponse> deleteProjectSkill(@PathParam("projectId") String projectId, ProjectSkillRequest projectSkillRequest) {
        List<SkillRequest> updatedSkills = commandBus
                .send(new RemoveProjectRequiredSkill(
                        projectId,
                        projectSkillRequest.skillName()
                ));

        return getProjectSkillResponses(projectId, updatedSkills);
    }

    private List<ProjectSkillResponse> getProjectSkillResponses(String projectId, List<SkillRequest> updatedRequiredSkill) {
        return updatedRequiredSkill.stream()
                .map(skill -> new ProjectSkillResponse(projectId, skill.skillName(), skill.requiredLevel()))
                .toList();
    }


    private List<ProjectProfessionResponse> getProjectProfessionResponses(String projectId, List<String> updatedProfessions) {
        return updatedProfessions.stream()
                .map(professionName -> new ProjectProfessionResponse(projectId, professionName))
                .toList();
    }

    private ProjectResponse getProjectResponse(Project project) {
        return new ProjectResponse(
                project.projectId().value(),
                project.taskName().value(),
                project.requiredSkills().stream().map(skill -> skill.skillName().value()).toList(),
                project.professions().stream().map(profession -> profession.professionName().value()).toList(),
                project.contractorId().value(),
                project.tradesmenIds().stream().map(EntityId::value).toList(),
                project.period().startDate(),
                project.period().endDate(),
                project.dailyRate().amount().value(),
                project.location().coordinate().longitude(),
                project.location().coordinate().latitude(),
                project.location().locationName().value()
        );
    }
}
