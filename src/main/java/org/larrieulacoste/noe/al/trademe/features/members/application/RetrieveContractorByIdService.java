package org.larrieulacoste.noe.al.trademe.features.members.application;

import org.larrieulacoste.noe.al.trademe.features.members.domain.Contractor;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Contractors;
import org.larrieulacoste.noe.al.trademe.kernel.query.QueryHandler;

import java.util.Objects;

public class RetrieveContractorByIdService implements QueryHandler<RetrieveContractorById, Contractor> {
    private final Contractors contractors;

    public RetrieveContractorByIdService(Contractors contractors) {
        this.contractors = Objects.requireNonNull(contractors);
    }

    @Override
    public Contractor handle(RetrieveContractorById command) {
        return contractors.byId(command.contractorId);
    }
}
