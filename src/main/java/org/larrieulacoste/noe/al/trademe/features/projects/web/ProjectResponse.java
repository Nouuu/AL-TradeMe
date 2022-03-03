package org.larrieulacoste.noe.al.trademe.features.projects.web;

import java.time.ZonedDateTime;
import java.util.List;

record ProjectResponse(
        String projectId,
        String taskName,
        List<String> requiredSkills,
        List<String> professions,
        String contractorId,
        List<String> tradesmenIds,
        ZonedDateTime startDate,
        ZonedDateTime endDate,
        double dailyRate,
        double longitude,
        double latitude,
        String locationName
) {
}
