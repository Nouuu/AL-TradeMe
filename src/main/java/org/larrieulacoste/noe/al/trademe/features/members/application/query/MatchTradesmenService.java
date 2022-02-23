package org.larrieulacoste.noe.al.trademe.features.members.application.query;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import org.larrieulacoste.noe.al.trademe.application.event.TradesmenMatched;
import org.larrieulacoste.noe.al.trademe.domain.model.Coordinate;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.domain.model.Location;
import org.larrieulacoste.noe.al.trademe.domain.model.Period;
import org.larrieulacoste.noe.al.trademe.domain.model.Profession;
import org.larrieulacoste.noe.al.trademe.features.members.domain.NotEmptyString;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesman;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesmen;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;
import org.larrieulacoste.noe.al.trademe.kernel.query.QueryHandler;
import org.larrieulacoste.noe.al.trademe.kernel.validators.DateValidators;
import org.larrieulacoste.noe.al.trademe.kernel.validators.StringValidators;

@ApplicationScoped
public class MatchTradesmenService implements QueryHandler<MatchTradesmen, List<Tradesman>> {
    private final Tradesmen tradesmen;
    private final StringValidators stringValidators;
    private final EventBus<ApplicationEvent> eventBus;
    private final DateValidators dateValidators;

    MatchTradesmenService(Tradesmen tradesmen, StringValidators stringValidators, EventBus<ApplicationEvent> eventBus, DateValidators dateValidators) {
        this.tradesmen = tradesmen;
        this.stringValidators = stringValidators;
        this.eventBus = eventBus;
        this.dateValidators = dateValidators;
    }

    @Override
    public List<Tradesman> handle(MatchTradesmen command) {
        Profession requiredProfession = Profession.of(NotEmptyString.of(command.profession(), stringValidators));
        Location projectLocation = Location.of(Coordinate.of(command.longitude(), command.latitude()), NotEmptyString.of("", stringValidators));
        Period projectPeriod = Period.of(command.startDate(), command.endDate(), dateValidators);
        EntityId projectId = EntityId.of(command.projectId());
        List<Tradesman> matchedTradesmen = tradesmen.findAll()
                .stream()
                .filter(tradesman -> tradesman.professionalAbilities()
                        .activityRadius()
                        .activityRadius() >= tradesman.address()
                        .distance(projectLocation))
                .filter(tradesman -> tradesman.professionalAbilities()
                        .profession()
                        .equals(requiredProfession))
                .filter(tradesman -> tradesman.professionalAbilities()
                        .activityRadius()
                        .activityRadius() >= tradesman.address()
                        .distance(projectLocation))
                .filter(tradesman -> tradesman.professionalAbilities()
                        .unavailability()
                        .stream()
                        .allMatch(period -> projectPeriod.startDate().isAfter(period.endDate()) || projectPeriod.endDate().isBefore(period.startDate())))
                .filter(tradesman -> tradesman.professionalAbilities()
                        .dailyRate()
                        .amount()
                        .value() < (command.dailyRate() * 1.1)
                        && tradesman.professionalAbilities()
                        .dailyRate()
                        .amount()
                        .value() > (command.dailyRate() * 0.9)  )
                .toList();

        eventBus.publish(
                TradesmenMatched.of(projectId, matchedTradesmen.stream()
                        .map(Tradesman::entityId)
                        .toList()));
        return matchedTradesmen;
    }
}
