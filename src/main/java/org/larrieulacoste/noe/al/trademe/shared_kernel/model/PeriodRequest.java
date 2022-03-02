package org.larrieulacoste.noe.al.trademe.shared_kernel.model;

import java.time.ZonedDateTime;

public record PeriodRequest(ZonedDateTime startDate, ZonedDateTime endDate) {
}
