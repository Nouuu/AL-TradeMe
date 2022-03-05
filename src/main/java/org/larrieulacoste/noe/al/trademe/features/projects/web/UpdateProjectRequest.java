package org.larrieulacoste.noe.al.trademe.features.projects.web;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

record UpdateProjectRequest(
        @Schema(defaultValue = "Building renovation") String taskName,
        @Schema(defaultValue = "50") Double dailyRate,
        @Schema(defaultValue = "Paris") String locationName,
        @Schema(defaultValue = "2") Double longitude, // Y
        @Schema(defaultValue = "48") Double latitude // X
) {
}
