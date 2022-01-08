package org.larrieulacoste.noe.al.trademe.features.members.web;


import org.eclipse.microprofile.openapi.annotations.Operation;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.features.members.application.command.CreateTradesman;
import org.larrieulacoste.noe.al.trademe.features.members.application.command.DeleteTradesman;
import org.larrieulacoste.noe.al.trademe.features.members.application.command.UpdateTradesman;
import org.larrieulacoste.noe.al.trademe.features.members.application.query.RetrieveTradesmanById;
import org.larrieulacoste.noe.al.trademe.features.members.application.query.RetrieveTradesmen;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesman;
import org.larrieulacoste.noe.al.trademe.features.members.kernel.MembersCommandBus;
import org.larrieulacoste.noe.al.trademe.features.members.kernel.MembersQueryBus;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("tradesman")
class TradesmanController {
    private final MembersQueryBus queryBus;
    private final MembersCommandBus commandBus;

    TradesmanController(MembersQueryBus queryBus, MembersCommandBus commandBus) {
        this.queryBus = queryBus;
        this.commandBus = commandBus;
    }

    @GET
    @Path("{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public TradesmanResponse getById(@PathParam("userId") String userId) {
        Tradesman tradesman = queryBus.send(new RetrieveTradesmanById(EntityId.of(userId)));
        return new TradesmanResponse(
                tradesman.getEntityId().value,
                tradesman.getFirstname().value,
                tradesman.getLastname().value,
                tradesman.getEmail().value
        );
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public TradesmenResponse getAll() {
        List<Tradesman> tradesmen = queryBus.send(new RetrieveTradesmen());

        return new TradesmenResponse(
                tradesmen.stream().map(tradesman -> new TradesmanResponse(
                        tradesman.getEntityId().value,
                        tradesman.getFirstname().value,
                        tradesman.getLastname().value,
                        tradesman.getEmail().value
                )).collect(Collectors.toList()),
                tradesmen.size());
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public TradesmanResponse register(TradesmanRequest tradesman) {
        EntityId userId = commandBus.send(new CreateTradesman(
                tradesman.firstname,
                tradesman.lastname,
                tradesman.email,
                tradesman.password,
                tradesman.paymentMethodType,
                tradesman.paymentMethodRessource));

        return new TradesmanResponse(userId.value, null, null, null);
    }

    @PUT
    @Path("{tradesmanId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public TradesmanResponse register(@PathParam("tradesmanId") String tradesmanId, TradesmanRequest tradesman) {
        Tradesman updatedTradesman = commandBus.send(new UpdateTradesman(
                tradesmanId,
                tradesman.firstname,
                tradesman.lastname,
                tradesman.email,
                tradesman.password
        ));

        return new TradesmanResponse(updatedTradesman.getEntityId().value,
                updatedTradesman.getFirstname().value,
                updatedTradesman.getLastname().value,
                updatedTradesman.getEmail().value
        );
    }

    @DELETE
    @Path("{tradesmanId}")
    @Operation(summary = "Delete tradesman", description = "Delete tradesman from TradeMe")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public TradesmanResponse delete(@PathParam("tradesmanId") String tradesmanId) {
        commandBus.send(new DeleteTradesman(tradesmanId));

        return new TradesmanResponse(
                tradesmanId,
                null, null, null
        );
    }

}
