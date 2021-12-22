package org.larrieulacoste.noe.al.trademe.features.members.web;


import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.features.members.application.CreateTradesman;
import org.larrieulacoste.noe.al.trademe.features.members.application.RetrieveTradesmanById;
import org.larrieulacoste.noe.al.trademe.features.members.application.RetrieveTradesmen;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesman;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.query.QueryBus;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("tradesman")
public class TradesmanController {
    private final QueryBus queryBus;
    private final CommandBus commandBus;

    public TradesmanController(QueryBus queryBus, CommandBus commandBus) {
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
}
