package org.larrieulacoste.noe.al.trademe.features.members.application.query;

import org.larrieulacoste.noe.al.trademe.domain.model.Coordinate;
import org.larrieulacoste.noe.al.trademe.domain.model.Location;
import org.larrieulacoste.noe.al.trademe.domain.model.Profession;
import org.larrieulacoste.noe.al.trademe.features.members.domain.NotEmptyString;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesman;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesmen;
import org.larrieulacoste.noe.al.trademe.kernel.query.QueryHandler;
import org.larrieulacoste.noe.al.trademe.kernel.validators.StringValidators;

import java.util.List;

public class MatchTradesmenService implements QueryHandler<MatchTradesmen, List<Tradesman>> {
    private final Tradesmen tradesmen;
    private final StringValidators stringValidators;

    MatchTradesmenService(Tradesmen tradesmen, StringValidators stringValidators) {
        this.tradesmen = tradesmen;
        this.stringValidators = stringValidators;
    }

    @Override
    public List<Tradesman> handle(MatchTradesmen command) {
        Profession requiredProfession = Profession.of(NotEmptyString.of(command.profession(), stringValidators));
        Location projectLocation = Location.of(Coordinate.of(command.longitude(), command.latitude()), NotEmptyString.of("", stringValidators));
        return tradesmen.findAll()
                .stream()
                .filter(tradesman -> tradesman.professionalAbilities()
                        .activityRadius()
                        .activityRadius() >= tradesman.professionalAbilities()
                        .address()
                        .distance(projectLocation))
                .filter(tradesman -> tradesman.professionalAbilities()
                        .profession()
                        .equals(requiredProfession))
                .toList();
    }
}
