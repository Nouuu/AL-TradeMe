package org.larrieulacoste.noe.al.trademe.features.members.application.query;

import org.larrieulacoste.noe.al.trademe.features.members.domain.Contractor;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Contractors;
import org.larrieulacoste.noe.al.trademe.kernel.query.QueryHandler;

import javax.enterprise.context.ApplicationScoped;
import java.util.Objects;

@ApplicationScoped
public class RetrieveContractorByIdService implements QueryHandler<RetrieveContractorById, Contractor> {
    private final Contractors contractors;

    RetrieveContractorByIdService(Contractors contractors) {
        this.contractors = Objects.requireNonNull(contractors);
    }

    @Override
    public Contractor handle(RetrieveContractorById query) {
        return contractors.byId(query.contractorId);
    }
}
