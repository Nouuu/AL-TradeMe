package org.larrieulacoste.noe.al.trademe.features.members.application;

import org.larrieulacoste.noe.al.trademe.features.members.domain.Contractor;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Contractors;
import org.larrieulacoste.noe.al.trademe.features.members.infrastructure.kernel.query.QueryHandler;

import java.util.List;
import java.util.Objects;

public class RetrieveContractorsService implements QueryHandler<RetrieveContractors, List<Contractor>> {
    private final Contractors contractors;

    public RetrieveContractorsService(Contractors contractors) {
        this.contractors = Objects.requireNonNull(contractors);
    }

    @Override
    public List<Contractor> handle(RetrieveContractors command) {
        return contractors.findAll();
    }
}
