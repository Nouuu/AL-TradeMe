package org.larrieulacoste.noe.al.trademe.kernel.validators;

import java.time.ZonedDateTime;

public interface DateValidators {
    boolean isValidPeriod(ZonedDateTime start, ZonedDateTime end);
}
