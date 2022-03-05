package org.larrieulacoste.noe.al.trademe.features.members.web;

import java.time.ZonedDateTime;
import java.util.List;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.SkillRequest;

public record MatchTradesmanRequest(
        String projectId,
        List<SkillRequest> requiredSkills,
        List<String> requiredProfessions,
        @Schema(defaultValue = "2023-03-01T00:00:00.000Z") ZonedDateTime startDate,
        @Schema(defaultValue = "2023-03-31T00:00:00.000Z") ZonedDateTime endDate,
        @Schema(defaultValue = "50") double dailyRate,
        @Schema(defaultValue = "48") double latitude,
        @Schema(defaultValue = "2") double longitude,
        @Schema(defaultValue = "Paris") String locationName
) {
}
