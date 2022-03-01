package org.larrieulacoste.noe.al.trademe.features.members.application.query;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.domain.model.SkillRequest;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesman;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesmen;
import org.larrieulacoste.noe.al.trademe.kernel.query.QueryHandler;

@ApplicationScoped
public class RetrieveTradesmanSkillsService implements QueryHandler<RetrieveTradesmanSkills, List<SkillRequest>> {
    private final Tradesmen tradesmen;

    public RetrieveTradesmanSkillsService(Tradesmen tradesmen) {
        this.tradesmen = tradesmen;
    }

    @Override
    public List<SkillRequest> handle(RetrieveTradesmanSkills command) {
        Tradesman tradesman = tradesmen.byId(EntityId.of(command.tradesmanId()));

        return tradesman.professionalAbilities().skills().stream().map(currentRequiredSkill ->
                        new SkillRequest(currentRequiredSkill.skillName().value(), currentRequiredSkill.requiredLevel()))
                .toList();
    }
}
