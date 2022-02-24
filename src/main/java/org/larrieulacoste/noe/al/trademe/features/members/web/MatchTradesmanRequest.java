package org.larrieulacoste.noe.al.trademe.features.members.web;

import java.time.ZonedDateTime;
import java.util.List;
import org.larrieulacoste.noe.al.trademe.domain.model.Skill;

public record MatchTradesmanRequest(String projectId,
    List<Skill> requiredSkills,
    String profession,
    ZonedDateTime startDate,
    ZonedDateTime endDate,
    double dailyRate,
    double latitude,
    double longitude) {
}
