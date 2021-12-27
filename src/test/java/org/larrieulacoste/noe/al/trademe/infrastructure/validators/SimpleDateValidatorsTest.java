package org.larrieulacoste.noe.al.trademe.infrastructure.validators;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class SimpleDateValidatorsTest {
    private final SimpleDateValidators simpleDateValidators = new SimpleDateValidators();
    private LocalDate start;
    private LocalDate end;

    @BeforeEach
    void setUp() {
        start = LocalDate.now();
        end = start.plusDays(3);
    }

    @Test
    void isValidPeriod() {
        Assertions.assertThat(simpleDateValidators.isValidPeriod(start, end)).isTrue();
    }
    @Test
    void isValidPeriodInvalidStart() {
        Assertions.assertThat(simpleDateValidators.isValidPeriod(null, end)).isFalse();
    }
    @Test
    void isValidPeriodInvalidEnd() {
        Assertions.assertThat(simpleDateValidators.isValidPeriod(start, null)).isFalse();
    }
    @Test
    void isValidPeriodInvalidRange() {
        end = start;
        Assertions.assertThat(simpleDateValidators.isValidPeriod(start, end)).isFalse();
    }
    @Test
    void isValidPeriodInvalidRange2() {
        end = start.minusDays(1);
        Assertions.assertThat(simpleDateValidators.isValidPeriod(start, end)).isFalse();
    }
}