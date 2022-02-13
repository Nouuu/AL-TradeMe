package org.larrieulacoste.noe.al.trademe.features.members.web;


import org.eclipse.microprofile.openapi.annotations.Operation;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.features.members.application.command.CreateContractor;
import org.larrieulacoste.noe.al.trademe.features.members.application.command.DeleteContractor;
import org.larrieulacoste.noe.al.trademe.features.members.application.command.UpdateContractor;
import org.larrieulacoste.noe.al.trademe.features.members.application.query.RetrieveContractorById;
import org.larrieulacoste.noe.al.trademe.features.members.application.query.RetrieveContractors;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Contractor;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.query.QueryBus;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("contractor")
@Produces(MediaType.APPLICATION_JSON)
public final class ContractorController {
    private final QueryBus queryBus;
    private final CommandBus commandBus;

    ContractorController(QueryBus queryBus, CommandBus commandBus) {
        this.queryBus = queryBus;
        this.commandBus = commandBus;
    }

    @GET
    @Path("{userId}")
    @Operation(summary = "Retrieve contractor by ID", description = "Retrieve contractor giving contractor's ID")
    public ContractorResponse getById(@PathParam("userId") String userId) {
        Contractor contractor = queryBus.send(new RetrieveContractorById(EntityId.of(userId)));
        return getContractorResponse(contractor);
    }

    @GET
    @Operation(summary = "Retrieve contractors", description = "Retrieve all contractors")
    public ContractorsResponse getAll() {
        List<Contractor> contractors = queryBus.send(new RetrieveContractors());

        return getContractorsResponse(contractors);
    }

    @POST
    @Operation(summary = "Create contractor", description = "Register a new contractor to TradeMe")
    @Consumes(MediaType.APPLICATION_JSON)
    public ContractorResponse register(ContractorRequest contractor) {
        EntityId userId = commandBus.send(new CreateContractor(
                contractor.firstname(),
                contractor.lastname(),
                contractor.email(),
                contractor.password(),
                contractor.paymentMethodType(),
                contractor.paymentMethodRessource()));

        return new ContractorResponse(userId.value(), null, null, null);
    }

    @PUT
    @Path("{contractorId}")
    @Operation(summary = "Update contractor", description = "Update contractor in TradeMe")
    @Consumes(MediaType.APPLICATION_JSON)
    public ContractorResponse update(@PathParam("contractorId") String contractorId, ContractorRequest contractor) {
        Contractor updatedContractor = commandBus.send(new UpdateContractor(
                contractorId,
                contractor.firstname(),
                contractor.lastname(),
                contractor.email(),
                contractor.password()
        ));

        return getContractorResponse(updatedContractor);
    }

    @DELETE
    @Path("{contractorId}")
    @Operation(summary = "Delete contractor", description = "Delete contractor from TradeMe")
    @Consumes(MediaType.APPLICATION_JSON)
    public ContractorResponse delete(@PathParam("contractorId") String contractorId) {
        commandBus.send(new DeleteContractor(contractorId));

        return new ContractorResponse(contractorId, null, null, null);
    }

    private ContractorsResponse getContractorsResponse(List<Contractor> contractors) {
        return new ContractorsResponse(
                contractors.stream().map(this::getContractorResponse).toList(),
                contractors.size());
    }

    private ContractorResponse getContractorResponse(Contractor contractor) {
        return new ContractorResponse(
                contractor.entityId().value(),
                contractor.firstname().value,
                contractor.lastname().value,
                contractor.email().value
        );
    }
}
