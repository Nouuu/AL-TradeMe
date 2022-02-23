package org.larrieulacoste.noe.al.trademe.features.members.application.query;

import java.time.ZonedDateTime;
import org.larrieulacoste.noe.al.trademe.kernel.query.Query;

import java.util.List;

public record MatchTradesmen(String projectId,
     List<String> requiredSkills,
     String profession,
     ZonedDateTime startDate,
     ZonedDateTime endDate,
     double dailyRate,
     double latitude,
     double longitude) implements Query {
}