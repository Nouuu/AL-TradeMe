package org.larrieulacoste.noe.al.trademe.features.members.web;


import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.features.members.application.command.CreateTradesman;
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
public class TradesmanController {
    private final MembersQueryBus queryBus;
    private final MembersCommandBus commandBus;

    public TradesmanController(MembersQueryBus queryBus, MembersCommandBus commandBus) {
        this.queryBus = queryBus;
        this.commandBus = commandBus;
    }

    @GET
    @Path("{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public TradesmanResponse getById(@PathParam("userId") String userId) {
        Tradesman tradesman = queryBus.send(new RetrieveTradesmanById(EntityId.of(userId)));
        return new TradesmanResponse(
                tradesman.getEntityId().getValue(),
                tradesman.getFirstname().getField(),
                tradesman.getLastname().getField(),
                tradesman.getEmail().getEmailAddressString()
        );
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public TradesmenResponse getAll() {
        List<Tradesman> tradesmen = queryBus.send(new RetrieveTradesmen());

        return new TradesmenResponse(
                tradesmen.stream().map(tradesman -> new TradesmanResponse(
                        tradesman.getEntityId().getValue(),
                        tradesman.getFirstname().getField(),
                        tradesman.getLastname().getField(),
                        tradesman.getEmail().getEmailAddressString()
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
                tradesman.password
        ));

        return new TradesmanResponse(userId.getValue(), null, null, null);
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

        return new TradesmanResponse(updatedTradesman.getEntityId().getValue(),
                updatedTradesman.getFirstname().getField(),
                updatedTradesman.getLastname().getField(),
                updatedTradesman.getEmail().getEmailAddressString()
        );
    }
}
