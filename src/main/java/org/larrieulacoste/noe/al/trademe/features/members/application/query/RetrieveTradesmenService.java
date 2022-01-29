package org.larrieulacoste.noe.al.trademe.features.members.application.query;

import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesman;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesmans;
import org.larrieulacoste.noe.al.trademe.kernel.query.QueryHandler;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class RetrieveTradesmenService implements QueryHandler<RetrieveTradesmen, List<Tradesman>> {
    private final Tradesmans tradesmans;

    RetrieveTradesmenService(Tradesmans tradesmans) {
        this.tradesmans = tradesmans;
    }

    @Override
    public List<Tradesman> handle(RetrieveTradesmen query) {
        return tradesmans.findAll();
    }
}
