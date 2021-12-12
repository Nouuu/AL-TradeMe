package org.larrieulacoste.noe.al.trademe.domain.validators;

import java.time.LocalDate;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class DateValidators {

    private DateValidators() {
    }

    public static boolean isValidPeriod(LocalDate start, LocalDate end) {
        return start != null && end != null && start.isBefore(end);
    }
}
