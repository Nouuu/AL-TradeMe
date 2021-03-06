package org.larrieulacoste.noe.al.trademe.kernel.validators;

import java.time.ZonedDateTime;

public final class SimpleDateValidators implements DateValidators {

    @Override
    public boolean isValidPeriod(ZonedDateTime start, ZonedDateTime end) {
        return start != null && end != null && start.isBefore(end);
    }
}
