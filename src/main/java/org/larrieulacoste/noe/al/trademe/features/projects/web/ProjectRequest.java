package org.larrieulacoste.noe.al.trademe.features.projects.web;

import java.time.ZonedDateTime;
import java.util.List;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.SkillRequest;

record ProjectRequest(
        @Schema(defaultValue = "Building renovation") String taskName,
        List<SkillRequest> skills,
        @Schema(enumeration = {"Plumber", "Electrician"}) List<String> professions,
        String contractorId,
        @Schema(defaultValue = "2023-03-01T00:00:00.000Z") ZonedDateTime startDate,
        @Schema(defaultValue = "2023-03-31T00:00:00.000Z") ZonedDateTime endDate,
        @Schema(defaultValue = "50") Double dailyRate,
        @Schema(defaultValue = "Paris")String locationName,
        @Schema(defaultValue = "2") Double longitude, // Y
        @Schema(defaultValue = "48") Double latitude // X
) {
}
