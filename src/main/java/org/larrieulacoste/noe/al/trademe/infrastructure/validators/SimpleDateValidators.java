package org.larrieulacoste.noe.al.trademe.infrastructure.validators;

import org.larrieulacoste.noe.al.trademe.domain.validators.DateValidators;

import java.time.ZonedDateTime;

public final class SimpleDateValidators implements DateValidators {

    @Override
    public boolean isValidPeriod(ZonedDateTime start, ZonedDateTime end) {
        return start != null && end != null && start.isBefore(end);
    }
}
