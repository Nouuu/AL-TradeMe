package org.larrieulacoste.noe.al.trademe.features.projects.web;

import org.larrieulacoste.noe.al.trademe.domain.model.SkillRequest;

import java.time.ZonedDateTime;
import java.util.List;

record ProjectRequest(
        String taskName,
        List<SkillRequest> skills,
        List<String> professions,
        String contractorId,
        ZonedDateTime startDate,
        ZonedDateTime endDate,
        Double dailyRate,
        String locationName,
        Double longitude, // Y
        Double latitude // X
) {
}
