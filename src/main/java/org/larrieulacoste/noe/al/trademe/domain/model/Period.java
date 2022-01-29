package org.larrieulacoste.noe.al.trademe.domain.model;

import org.larrieulacoste.noe.al.trademe.domain.exception.InvalidPeriodException;
import org.larrieulacoste.noe.al.trademe.kernel.validators.ValidatorsAccessor;

import java.time.ZonedDateTime;

public final class Period {
    public final ZonedDateTime startDate;
    public final ZonedDateTime endDate;

    private Period(ZonedDateTime startDate, ZonedDateTime endDate) {
        if (!ValidatorsAccessor.getDateValidatorsInstance().isValidPeriod(startDate, endDate)) {
            throw new InvalidPeriodException("Invalid period " + startDate + " - " + endDate);
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static Period of(ZonedDateTime startDate, ZonedDateTime endDate) {
        return new Period(startDate, endDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Period period = (Period) o;

        if (!startDate.equals(period.startDate))
            return false;
        return endDate.equals(period.endDate);
    }

    @Override
    public int hashCode() {
        int result = startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Period{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
