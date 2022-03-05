package org.larrieulacoste.noe.al.trademe.features.members.web;

import java.util.List;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.SkillRequest;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

record TradesmanAbilitiesRequest(
        String tradesmanId,
        @Schema(defaultValue = "Plumber") String profession,
        List<SkillRequest> skills,
        @Schema(defaultValue = "10") Double activityRadius,
        @Schema(defaultValue = "50") Double dailyRate
) {
}
