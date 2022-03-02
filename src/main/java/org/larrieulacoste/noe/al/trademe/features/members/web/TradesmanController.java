package org.larrieulacoste.noe.al.trademe.features.members.web;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.larrieulacoste.noe.al.trademe.features.members.application.command.CreateTradesman;
import org.larrieulacoste.noe.al.trademe.features.members.application.command.DeleteTradesman;
import org.larrieulacoste.noe.al.trademe.features.members.application.command.UpdateTradesman;
import org.larrieulacoste.noe.al.trademe.features.members.application.command.UpdateTradesmanAbilities;
import org.larrieulacoste.noe.al.trademe.features.members.application.query.MatchTradesmen;
import org.larrieulacoste.noe.al.trademe.features.members.application.query.RetrieveTradesmanById;
import org.larrieulacoste.noe.al.trademe.features.members.application.query.RetrieveTradesmen;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesman;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.query.QueryBus;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("tradesman")
@Produces(MediaType.APPLICATION_JSON)
public final class TradesmanController {
    private final QueryBus queryBus;
    private final CommandBus commandBus;

    TradesmanController(QueryBus queryBus, CommandBus commandBus) {
        this.queryBus = queryBus;
        this.commandBus = commandBus;
    }

    @POST
    @Path("match")
    @Operation(summary = "Match tradesmen", description = "Retrieve tradesmen matching abilities criterion")
    @Consumes(MediaType.APPLICATION_JSON)
    public TradesmenResponse matchTradesman(MatchTradesmanRequest projectCriterion) {
        List<Tradesman> tradesmen = queryBus.send(new MatchTradesmen(projectCriterion.projectId(),
                projectCriterion.requiredSkills(),
                projectCriterion.requiredProfessions(),
                projectCriterion.startDate(),
                projectCriterion.endDate(),
                projectCriterion.dailyRate(),
                projectCriterion.latitude(),
                projectCriterion.longitude(),
                projectCriterion.locationName()));

        return getTradesmenResponse(tradesmen);
    }

    @GET
    @Path("{userId}")
    @Operation(summary = "Retrieve tradesman by ID", description = "Retrieve tradesman giving tradesman's ID")
    public TradesmanResponse getById(@PathParam("userId") String userId) {
        Tradesman tradesman = queryBus.send(new RetrieveTradesmanById(EntityId.of(userId)));
        return getTradesmanResponse(tradesman);
    }

    @GET
    @Operation(summary = "Retrieve tradesmen", description = "Retrieve all tradesmen")
    public TradesmenResponse getAll() {
        List<Tradesman> tradesmen = queryBus.send(new RetrieveTradesmen());

        return getTradesmenResponse(tradesmen);
    }

    @POST
    @Operation(summary = "Create tradesman", description = "Register a new tradesman to TradeMe")
    @Consumes(MediaType.APPLICATION_JSON)
    public EntityId register(TradesmanRequest tradesman) {
        return commandBus.send(new CreateTradesman(
                tradesman.firstname(),
                tradesman.lastname(),
                tradesman.email(),
                tradesman.password(),
                tradesman.paymentMethodType(),
                tradesman.paymentMethodRessource(),
                tradesman.profession(),
                tradesman.longitude(),
                tradesman.latitude(),
                tradesman.activityRadius(),
                tradesman.dailyRate(),
                tradesman.locationName()));
    }

    @PUT
    @Path("{tradesmanId}")
    @Operation(summary = "Update tradesman", description = "Update tradesman in TradeMe")
    @Consumes(MediaType.APPLICATION_JSON)
    public TradesmanResponse update(@PathParam("tradesmanId") String tradesmanId, TradesmanRequest tradesman) {
        Tradesman updatedTradesman = commandBus.send(new UpdateTradesman(
                tradesmanId,
                tradesman.firstname(),
                tradesman.lastname(),
                tradesman.email(),
                tradesman.password(),
                tradesman.locationName(),
                tradesman.longitude(),
                tradesman.latitude()));

        return getTradesmanResponse(updatedTradesman);
    }

    @PUT
    @Path("abilities/{tradesmanId}")
    @Operation(summary = "Update tradesman abilities", description = "Update tradesman abilities in TradeMe")
    @Consumes(MediaType.APPLICATION_JSON)
    public TradesmanResponse updateAbilities(@PathParam("tradesmanId") String tradesmanId,
            TradesmanAbilitiesRequest abilities) {
        Tradesman updatedTradesman = commandBus.send(new UpdateTradesmanAbilities(
                tradesmanId,
                abilities.profession(),
                abilities.skills(),
                abilities.activityRadius(),
                abilities.dailyRate(),
                abilities.unavailabilityPeriods()));

        return getTradesmanResponse(updatedTradesman);
    }

    @DELETE
    @Path("{tradesmanId}")
    @Operation(summary = "Delete tradesman", description = "Delete tradesman from TradeMe")
    @Consumes(MediaType.APPLICATION_JSON)
    public EntityId delete(@PathParam("tradesmanId") String tradesmanId) {
        commandBus.send(new DeleteTradesman(tradesmanId));

        return EntityId.of(tradesmanId);
    }

    private TradesmenResponse getTradesmenResponse(List<Tradesman> tradesmen) {
        return new TradesmenResponse(
                tradesmen.stream().map(this::getTradesmanResponse).toList(),
                tradesmen.size());
    }

    private TradesmanResponse getTradesmanResponse(Tradesman tradesman) {
        return new TradesmanResponse(
                tradesman.entityId().value(),
                tradesman.firstname().value(),
                tradesman.lastname().value(),
                tradesman.email().value(),
                tradesman.professionalAbilities().profession().professionName().value(),
                tradesman.professionalAbilities().dailyRate().amount().value(),
                tradesman.address().coordinate().longitude(),
                tradesman.address().coordinate().latitude(),
                tradesman.address().locationName().value(),
                getSkillResponses(tradesman.professionalAbilities().skills()),
                getPeriodResponses(tradesman.professionalAbilities().unavailability()));
    }

    private List<PeriodResponse> getPeriodResponses(List<Period> periods) {
        return periods.stream()
                .map(period -> new PeriodResponse(period.startDate(), period.endDate())).toList();
    }

    private List<SkillResponse> getSkillResponses(List<Skill> skills) {
        return skills.stream()
                .map(skill -> new SkillResponse(
                        skill.skillName().value(),
                        skill.requiredLevel()))
                .toList();
    }
}
