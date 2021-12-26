package org.larrieulacoste.noe.al.trademe.domain.validators;

import java.time.LocalDate;

public interface DateValidators {
    boolean isValidPeriod(LocalDate start, LocalDate end);
}
