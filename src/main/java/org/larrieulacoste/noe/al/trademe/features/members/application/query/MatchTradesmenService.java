package org.larrieulacoste.noe.al.trademe.features.members.application.query;

import javax.enterprise.context.ApplicationScoped;
import org.larrieulacoste.noe.al.trademe.application.event.TradesmenMatched;
import org.larrieulacoste.noe.al.trademe.domain.model.Coordinate;
import org.larrieulacoste.noe.al.trademe.domain.model.Location;
import org.larrieulacoste.noe.al.trademe.domain.model.Profession;
import org.larrieulacoste.noe.al.trademe.features.members.domain.NotEmptyString;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesman;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesmen;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;
import org.larrieulacoste.noe.al.trademe.kernel.query.QueryHandler;
import org.larrieulacoste.noe.al.trademe.kernel.validators.StringValidators;

import java.util.List;

@ApplicationScoped
public class MatchTradesmenService implements QueryHandler<MatchTradesmen, List<Tradesman>> {
    private final Tradesmen tradesmen;
    private final StringValidators stringValidators;
    private final EventBus<ApplicationEvent> eventBus;

    MatchTradesmenService(Tradesmen tradesmen, StringValidators stringValidators, EventBus<ApplicationEvent> eventBus) {
        this.tradesmen = tradesmen;
        this.stringValidators = stringValidators;
        this.eventBus = eventBus;
    }

    @Override
    public List<Tradesman> handle(MatchTradesmen command) {
        Profession requiredProfession = Profession.of(NotEmptyString.of(command.profession(), stringValidators));
        Location projectLocation = Location.of(Coordinate.of(command.longitude(), command.latitude()), NotEmptyString.of("", stringValidators));

        List<Tradesman> matchedTradesmen = tradesmen.findAll()
                .stream()
                .filter(tradesman -> tradesman.professionalAbilities()
                        .activityRadius()
                        .activityRadius() >= tradesman.address()
                        .distance(projectLocation))
                .filter(tradesman -> tradesman.professionalAbilities()
                        .profession()
                        .equals(requiredProfession))
                .toList();

        eventBus.publish(
                TradesmenMatched.of(command.projectId(), matchedTradesmen.stream()
                        .map(Tradesman::entityId)
                        .toList()));
        return matchedTradesmen;
    }
}
