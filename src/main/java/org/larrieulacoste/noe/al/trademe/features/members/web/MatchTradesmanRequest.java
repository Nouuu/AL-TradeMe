package org.larrieulacoste.noe.al.trademe.features.members.web;

import java.time.ZonedDateTime;
import java.util.List;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.SkillRequest;

public record MatchTradesmanRequest(String projectId,
    List<SkillRequest> requiredSkills,
    List<String> requiredProfessions,
    ZonedDateTime startDate,
    ZonedDateTime endDate,
    double dailyRate,
    double latitude,
    double longitude,
    String locationName) {
}
