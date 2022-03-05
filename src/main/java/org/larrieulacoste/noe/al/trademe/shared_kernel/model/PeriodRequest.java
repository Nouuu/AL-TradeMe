package org.larrieulacoste.noe.al.trademe.shared_kernel.model;

import java.time.ZonedDateTime;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

public record PeriodRequest(
        @Schema(defaultValue = "2023-03-01T00:00:00.000Z") ZonedDateTime startDate,
        @Schema(defaultValue = "2023-03-31T00:00:00.000Z") ZonedDateTime endDate
) {
}
