package org.larrieulacoste.noe.al.trademe.features.members.application.command;

import java.time.ZonedDateTime;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import org.larrieulacoste.noe.al.trademe.domain.model.Period;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesman;
import org.larrieulacoste.noe.al.trademe.features.members.domain.TradesmanBuilder;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesmen;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.validators.DateValidators;

@ApplicationScoped
public class ReleaseTradesmanService implements CommandHandler<ReleaseTradesman, Tradesman> {
    private final Tradesmen tradesmen;
    private final TradesmanBuilder tradesmanBuilder;
    private final DateValidators dateValidators;

    public ReleaseTradesmanService(Tradesmen tradesmen, TradesmanBuilder tradesmanBuilder, DateValidators dateValidators) {
        this.tradesmen = tradesmen;
        this.tradesmanBuilder = tradesmanBuilder;
        this.dateValidators = dateValidators;
    }

    @Override
    public Tradesman handle(ReleaseTradesman command) {
        ZonedDateTime now = ZonedDateTime.now();
        if (isProjectFinished(now, command.endDate())) {
            return null;
        }
        Tradesman oldTradesman = tradesmen.byId(command.tradesmanId());
        tradesmanBuilder.clear();
        tradesmanBuilder.withTradesman(oldTradesman);

        this.removeOldPeriod(oldTradesman, command.startDate(), command.endDate());
        this.addShortenedPeriod(command.startDate(), now);

        Tradesman newTradesman = tradesmanBuilder.build(oldTradesman.entityId());
        tradesmen.save(newTradesman);
        return newTradesman;
    }

    private void removeOldPeriod(Tradesman oldTradesman, ZonedDateTime startDate, ZonedDateTime endDate) {
        Period oldPeriod = Period.of(startDate, endDate, dateValidators);

        List<Period> unavailability = oldTradesman.professionalAbilities().unavailability();
        unavailability.remove(oldPeriod);
        tradesmanBuilder.withUnavailability(unavailability);
    }

    private void addShortenedPeriod(ZonedDateTime startDate, ZonedDateTime now) {
        if (isProjectNotStartedYet(now, startDate)) {
            Period newPeriod = Period.of(startDate, now, dateValidators);
            tradesmanBuilder.addUnavailability(newPeriod);
        }
    }

    private boolean isProjectNotStartedYet(ZonedDateTime now, ZonedDateTime startDate) {
        return now.isBefore(startDate);
    }

    private boolean isProjectFinished(ZonedDateTime now, ZonedDateTime endDate) {
        return now.isAfter(endDate);
    }
}
