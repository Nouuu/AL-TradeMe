package org.larrieulacoste.noe.al.trademe.features.members.application.query;

import org.larrieulacoste.noe.al.trademe.domain.event.TradesmenMatched;
import org.larrieulacoste.noe.al.trademe.features.members.domain.NotEmptyString;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesman;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesmen;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;
import org.larrieulacoste.noe.al.trademe.kernel.query.QueryHandler;
import org.larrieulacoste.noe.al.trademe.kernel.validators.DateValidators;
import org.larrieulacoste.noe.al.trademe.kernel.validators.StringValidators;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.*;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Stream;

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
        List<Profession> requiredProfessions = command.requiredProfessions()
                .stream()
                .map(profession -> Profession.of(NotEmptyString.of(profession, stringValidators)))
                .toList();
        List<Skill> requiredSkills = command.requiredSkills()
                .stream()
                .map(skillRequest -> Skill.of(NotEmptyString.of(skillRequest.skillName(), stringValidators),
                        skillRequest.requiredLevel()))
                .toList();
        Location projectLocation = Location.of(Coordinate.of(command.longitude(), command.latitude()), NotEmptyString.of(command.locationName(), stringValidators));
        Period projectPeriod = Period.of(command.startDate(), command.endDate(), dateValidators);
        EntityId projectId = EntityId.of(command.projectId());
        Stream<Tradesman> tradesmanStream = tradesmen.findAll().stream();
        tradesmanStream = filterLocation(tradesmanStream, projectLocation);
        tradesmanStream = filterAvailability(tradesmanStream, projectPeriod);
        tradesmanStream = filterProfession(tradesmanStream, requiredProfessions);
        tradesmanStream = filterDailyRate(tradesmanStream, command.dailyRate());
        tradesmanStream = filterSkills(tradesmanStream, requiredSkills);

        List<Tradesman> matchedTradesmen = tradesmanStream.toList();

        eventBus.publish(TradesmenMatched.of(projectId, matchedTradesmen.stream()
                .map(Tradesman::entityId)
                .toList()));
        return matchedTradesmen;
    }

    private Stream<Tradesman> filterLocation(Stream<Tradesman> tradesmanStream, Location projectLocation) {
        return tradesmanStream.filter(tradesman -> tradesman.professionalAbilities()
                .activityRadius()
                .activityRadius() >= tradesman.address()
                .distance(projectLocation));
    }

    private Stream<Tradesman> filterProfession(Stream<Tradesman> tradesmanStream, List<Profession> requiredProfession) {
        return tradesmanStream.filter(tradesman -> requiredProfession.contains(tradesman.professionalAbilities().profession()));
    }

    private Stream<Tradesman> filterSkills(Stream<Tradesman> tradesmanStream, List<Skill> requiredSkills) {
        return tradesmanStream.filter(tradesman -> requiredSkills.stream()
                .anyMatch(requiredSkill -> tradesman.professionalAbilities()
                        .skills().stream()
                        .anyMatch(tradesmanSkill -> tradesmanSkill.skillName().equals(requiredSkill.skillName()) &&
                                tradesmanSkill.requiredLevel() >= requiredSkill.requiredLevel()
                        )));
    }

    private Stream<Tradesman> filterAvailability(Stream<Tradesman> tradesmanStream, Period projectPeriod) {
        return tradesmanStream.filter(tradesman -> tradesman.professionalAbilities()
                .unavailability()
                .stream()
                .allMatch(period -> projectPeriod.startDate().isAfter(period.endDate())
                        || projectPeriod.endDate().isBefore(period.startDate())));
    }

    private Stream<Tradesman> filterDailyRate(Stream<Tradesman> tradesmanStream, double dailyRate) {
        return tradesmanStream.filter(tradesman -> tradesman.professionalAbilities()
                .dailyRate()
                .amount()
                .value() <= dailyRate);
    }
}
