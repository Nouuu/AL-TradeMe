package org.larrieulacoste.noe.al.trademe.domain.validators;

import java.time.ZonedDateTime;

public interface DateValidators {
    boolean isValidPeriod(ZonedDateTime start, ZonedDateTime end);
}
