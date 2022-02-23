package org.larrieulacoste.noe.al.trademe.features.members.web;

import java.time.ZonedDateTime;
import java.util.List;

public record MatchTradesmanRequest(String projectId,
    List<String> requiredSkills,
    String profession,
    ZonedDateTime startDate,
    ZonedDateTime endDate,
    double dailyRate,
    double latitude,
    double longitude) {
}
