package org.larrieulacoste.noe.al.trademe.features.projects.web;

import java.time.ZonedDateTime;

record UpdateProjectRequest(
        String taskName,
        ZonedDateTime startDate,
        ZonedDateTime endDate,
        Double dailyRate,
        String locationName,
        Double longitude, // Y
        Double latitude // X
) {
}
