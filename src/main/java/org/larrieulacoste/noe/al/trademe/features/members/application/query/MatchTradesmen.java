package org.larrieulacoste.noe.al.trademe.features.members.application.query;

import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.kernel.query.Query;

import java.util.Date;
import java.util.List;

public record MatchTradesmen(EntityId projectId,
     List<String> requiredSkills,
     String profession,
     Date startDate,
     Date endDate,
     double dailyRate,
     double latitude,
     double longitude) implements Query {
}
