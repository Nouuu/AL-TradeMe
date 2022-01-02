package org.larrieulacoste.noe.al.trademe.features.members.web;


import org.eclipse.microprofile.openapi.annotations.Operation;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.features.members.application.command.CreateContractor;
import org.larrieulacoste.noe.al.trademe.features.members.application.query.RetrieveContractorById;
import org.larrieulacoste.noe.al.trademe.features.members.application.query.RetrieveContractors;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Contractor;
import org.larrieulacoste.noe.al.trademe.features.members.kernel.MembersCommandBus;
import org.larrieulacoste.noe.al.trademe.features.members.kernel.MembersQueryBus;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.query.QueryBus;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("contractor")
public class ContractorController {
    private final MembersQueryBus queryBus;
    private final MembersCommandBus commandBus;

    public ContractorController(MembersQueryBus queryBus, MembersCommandBus commandBus) {
        this.queryBus = queryBus;
        this.commandBus = commandBus;
    }

    @GET
    @Path("{userId}")
    @Operation(summary = "Retrieve contractor by ID",description = "Retrieve contractor giving contractor's ID")
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
    @Operation(summary = "Retrieve contractors",description = "Retrieve all contractors")
    @Produces(MediaType.APPLICATION_JSON)
    public ContractorsResponse getAll() {
        List<Contractor> contractors = queryBus.send(new RetrieveContractors());

        return new ContractorsResponse(
                contractors.stream().map(contractor -> new ContractorResponse(
                        contractor.getEntityId().getValue(),
                        contractor.getFirstname().getField(),
                        contractor.getLastname().getField(),
                        contractor.getEmail().getEmailAddressString()
                )).collect(Collectors.toList()),
                contractors.size());
    }

    @POST
    @Operation(summary = "Create contractor",description = "Register a new contractor to TradeMe")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ContractorResponse register(ContractorRequest contractor) {
        EntityId userId = commandBus.send(new CreateContractor(
                contractor.firstname,
                contractor.lastname,
                contractor.email,
                contractor.password
        ));

        return new ContractorResponse(userId.getValue(), null, null, null);
    }
}
