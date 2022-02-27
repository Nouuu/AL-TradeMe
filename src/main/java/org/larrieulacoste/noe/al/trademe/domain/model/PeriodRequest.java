package org.larrieulacoste.noe.al.trademe.domain.model;

import java.time.ZonedDateTime;

public record PeriodRequest(ZonedDateTime startDate, ZonedDateTime endDate) {
}
