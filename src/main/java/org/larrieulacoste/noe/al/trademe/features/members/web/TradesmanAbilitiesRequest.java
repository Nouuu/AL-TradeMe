package org.larrieulacoste.noe.al.trademe.features.members.web;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

record TradesmanAbilitiesRequest(
        String tradesmanId,
        @Schema(defaultValue = "Plumber") String profession,
        @Schema(defaultValue = "10") Double activityRadius,
        @Schema(defaultValue = "50") Double dailyRate
) {
}
