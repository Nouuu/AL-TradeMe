package org.larrieulacoste.noe.al.trademe.domain.model;

import org.larrieulacoste.noe.al.trademe.domain.exception.InvalidPeriodException;
import org.larrieulacoste.noe.al.trademe.kernel.validators.DateValidators;

import java.time.ZonedDateTime;
import java.util.Objects;

public record Period(
        ZonedDateTime startDate,
        ZonedDateTime endDate
) {
    public Period {
        Objects.requireNonNull(startDate);
        Objects.requireNonNull(endDate);
    }

    private Period(ZonedDateTime startDate, ZonedDateTime endDate, DateValidators dateValidators) {
        this(startDate, endDate);
        if (!dateValidators.isValidPeriod(startDate, endDate)) {
            throw new InvalidPeriodException("Invalid period " + startDate + " - " + endDate);
        }
    }

    public static Period of(ZonedDateTime startDate, ZonedDateTime endDate, DateValidators dateValidators) {
        return new Period(startDate, endDate, dateValidators);
    }

}
