package org.larrieulacoste.noe.al.trademe.features.members.application.query;

import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesman;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesmen;
import org.larrieulacoste.noe.al.trademe.kernel.query.QueryHandler;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RetrieveTradesmanByIdService implements QueryHandler<RetrieveTradesmanById, Tradesman> {
    private final Tradesmen tradesmen;

    RetrieveTradesmanByIdService(Tradesmen tradesmen) {
        this.tradesmen = tradesmen;
    }

    @Override
    public Tradesman handle(RetrieveTradesmanById query) {
        return tradesmen.byId(query.tradesmanId());
    }
}
