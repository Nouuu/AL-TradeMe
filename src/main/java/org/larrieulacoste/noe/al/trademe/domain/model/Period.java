package org.larrieulacoste.noe.al.trademe.domain.model;

import org.larrieulacoste.noe.al.trademe.application.exception.InvalidPeriodException;
import org.larrieulacoste.noe.al.trademe.domain.validators.ValidatorsFactory;

import java.time.LocalDate;

public final class Period {
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Period(LocalDate startDate, LocalDate endDate) {
        if (!ValidatorsFactory.getDateValidatorsInstance().isValidPeriod(startDate, endDate)) {
            throw new InvalidPeriodException("Invalid period " + startDate + " - " + endDate);
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Period period = (Period) o;

        if (!startDate.equals(period.startDate)) return false;
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
