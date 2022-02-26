package org.larrieulacoste.noe.al.trademe.features.projects.web;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.features.projects.application.command.AssignTradesman;
import org.larrieulacoste.noe.al.trademe.features.projects.domain.Project;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.query.QueryBus;

@Path("project")
@Produces(MediaType.APPLICATION_JSON)
public final class ProjectController {
    private final QueryBus queryBus;
    private final CommandBus commandBus;

    ProjectController(QueryBus queryBus, CommandBus commandBus) {
        this.queryBus = queryBus;
        this.commandBus = commandBus;
    }

    @PUT
    @Path("{projectId}/assign/{tradesmanId}")
    @Operation(summary = "Update tradesman", description = "Update tradesman in TradeMe")
    @Consumes(MediaType.APPLICATION_JSON)
    public ProjectResponse update(@PathParam("projectId") String projectId, @PathParam("tradesmanId") String tradesmanId) {
        Project updatedTradesman = commandBus.send(new AssignTradesman(
                projectId,
                tradesmanId));

        return getProjectResponse(updatedTradesman);
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
