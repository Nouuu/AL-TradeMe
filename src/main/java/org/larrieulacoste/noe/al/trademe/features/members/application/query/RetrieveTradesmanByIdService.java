package org.larrieulacoste.noe.al.trademe.features.members.application.query;

import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesman;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesmans;
import org.larrieulacoste.noe.al.trademe.kernel.query.QueryHandler;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RetrieveTradesmanByIdService implements QueryHandler<RetrieveTradesmanById, Tradesman> {
    private final Tradesmans tradesmans;

    RetrieveTradesmanByIdService(Tradesmans tradesmans) {
        this.tradesmans = tradesmans;
    }

    @Override
    public Tradesman handle(RetrieveTradesmanById query) {
        return tradesmans.byId(query.tradesmanId);
    }
}
