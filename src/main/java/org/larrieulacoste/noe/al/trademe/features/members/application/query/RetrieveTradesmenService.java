package org.larrieulacoste.noe.al.trademe.features.members.application.query;

import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesman;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesmen;
import org.larrieulacoste.noe.al.trademe.kernel.query.QueryHandler;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class RetrieveTradesmenService implements QueryHandler<RetrieveTradesmen, List<Tradesman>> {
    private final Tradesmen tradesmen;

    public RetrieveTradesmenService(Tradesmen tradesmen) {
        this.tradesmen = tradesmen;
    }

    @Override
    public List<Tradesman> handle(RetrieveTradesmen command) {
        return tradesmen.findAll();
    }
}
