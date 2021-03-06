package org.larrieulacoste.noe.al.trademe.features.members.application.query;

import org.larrieulacoste.noe.al.trademe.features.members.domain.Contractor;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Contractors;
import org.larrieulacoste.noe.al.trademe.kernel.query.QueryHandler;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class RetrieveContractorsService implements QueryHandler<RetrieveContractors, List<Contractor>> {
    private final Contractors contractors;

    RetrieveContractorsService(Contractors contractors) {
        this.contractors = Objects.requireNonNull(contractors);
    }

    @Override
    public List<Contractor> handle(RetrieveContractors query) {
        return contractors.findAll();
    }
}
