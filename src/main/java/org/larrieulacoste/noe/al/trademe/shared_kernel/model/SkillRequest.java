package org.larrieulacoste.noe.al.trademe.shared_kernel.model;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public record SkillRequest(
        @Schema(defaultValue = "Safety") String skillName,
        @Schema(description = "The required level must be between 1 and 4", defaultValue = "1") Integer requiredLevel
) {
}
