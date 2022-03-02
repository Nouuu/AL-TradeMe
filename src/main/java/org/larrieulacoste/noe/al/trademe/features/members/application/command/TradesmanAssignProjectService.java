package org.larrieulacoste.noe.al.trademe.features.members.application.command;

import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesman;
import org.larrieulacoste.noe.al.trademe.features.members.domain.TradesmanBuilder;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesmen;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.validators.DateValidators;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.EntityId;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.Period;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TradesmanAssignProjectService
        implements CommandHandler<TradesmanAssignProject, Void> {
    private final Tradesmen tradesmen;
    private final TradesmanBuilder tradesmanBuilder;
    private final DateValidators dateValidators;

    public TradesmanAssignProjectService(Tradesmen tradesmen, TradesmanBuilder tradesmanBuilder, DateValidators dateValidators) {
        this.tradesmen = tradesmen;
        this.tradesmanBuilder = tradesmanBuilder;
        this.dateValidators = dateValidators;
    }

    @Override
    public Void handle(TradesmanAssignProject command) {
        EntityId tradesmanId = EntityId.of(command.tradesmanId());
        Period period = Period.of(command.startDate(), command.endDate(), dateValidators);

        Tradesman oldTradesman = tradesmen.byId(tradesmanId);

        tradesmanBuilder.clear();
        tradesmanBuilder.withTradesman(oldTradesman);
        tradesmanBuilder.addUnavailability(period);

        Tradesman newTradesman = tradesmanBuilder.build(oldTradesman.entityId());
        tradesmen.save(newTradesman);

        return null;
    }
}
