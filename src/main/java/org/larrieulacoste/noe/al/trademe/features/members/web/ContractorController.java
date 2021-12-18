package org.larrieulacoste.noe.al.trademe.features.members.web;


import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.features.members.application.CreateContractor;
import org.larrieulacoste.noe.al.trademe.features.members.application.RetrieveContractorById;
import org.larrieulacoste.noe.al.trademe.features.members.application.RetrieveContractors;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Contractor;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.query.QueryBus;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("contractor")
public class ContractorController {
    private final QueryBus queryBus;
    private final CommandBus commandBus;

    public ContractorController(QueryBus queryBus, CommandBus commandBus) {
        this.queryBus = queryBus;
        this.commandBus = commandBus;
    }

    @GET
    @Path("{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public ContractorResponse getById(@PathParam("userId") String userId) {
        Contractor contractor = queryBus.send(new RetrieveContractorById(EntityId.of(userId)));
        return new ContractorResponse(
                contractor.getEntityId().getValue(),
                contractor.getFirstname().getField(),
                contractor.getLastname().getField(),
                contractor.getEmail().getEmailAddressString()
        );
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ContractorsResponse getAll() {
        List<Contractor> contractors = queryBus.send(new RetrieveContractors());

        return new ContractorsResponse(
                contractors.stream().map(contractor -> new ContractorResponse(
                        contractor.getEntityId().getValue(),
                        contractor.getFirstname().getField(),
                        contractor.getLastname().getField(),
                        contractor.getEmail().getEmailAddressString()
                )).collect(Collectors.toList())
        );
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ContractorResponse create(ContractorRequest contractor) {
        EntityId userId = commandBus.send(new CreateContractor(
                contractor.firstname,
                contractor.lastname,
                contractor.email,
                contractor.password
        ));

        return new ContractorResponse(userId.getValue(), null, null, null);
    }
}
