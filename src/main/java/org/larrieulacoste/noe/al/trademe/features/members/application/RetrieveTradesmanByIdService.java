package org.larrieulacoste.noe.al.trademe.features.members.application;

import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesman;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesmen;
import org.larrieulacoste.noe.al.trademe.kernel.query.QueryHandler;

public class RetrieveTradesmanByIdService implements QueryHandler<RetrieveTradesmanById, Tradesman> {
    private final Tradesmen tradesmen;

    public RetrieveTradesmanByIdService(Tradesmen tradesmen) {
        this.tradesmen = tradesmen;
    }

    @Override
    public Tradesman handle(RetrieveTradesmanById command) {
        return tradesmen.byId(command.tradesmanId);
    }
}
