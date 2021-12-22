package org.larrieulacoste.noe.al.trademe.infrastructure.validators;

import org.larrieulacoste.noe.al.trademe.domain.validators.DateValidators;

import java.time.LocalDate;

public class SimpleDateValidators implements DateValidators {

    @Override
    public boolean isValidPeriod(LocalDate start, LocalDate end) {
        return start != null && end != null && start.isBefore(end);
    }
}
