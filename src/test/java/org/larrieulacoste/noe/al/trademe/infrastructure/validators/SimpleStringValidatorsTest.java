package org.larrieulacoste.noe.al.trademe.infrastructure.validators;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.larrieulacoste.noe.al.trademe.kernel.validators.SimpleStringValidators;

class SimpleStringValidatorsTest {
    private final SimpleStringValidators simpleStringValidators = new SimpleStringValidators();

    @Test
    void isNotEmptyOrOnlyWhitespaces() {
        Assertions.assertThat(simpleStringValidators.isNotEmptyOrOnlyWhitespaces("    ")).isFalse();
        Assertions.assertThat(simpleStringValidators.isNotEmptyOrOnlyWhitespaces(null)).isFalse();
        Assertions.assertThat(simpleStringValidators.isNotEmptyOrOnlyWhitespaces("\n\t ")).isFalse();
        Assertions.assertThat(simpleStringValidators.isNotEmptyOrOnlyWhitespaces("notEmpty")).isTrue();
    }

    @Test
    void isValidPassword() {
        Assertions.assertThat(simpleStringValidators.isValidPassword("password")).isTrue();
        Assertions.assertThat(simpleStringValidators.isValidPassword(null)).isFalse();
        Assertions.assertThat(simpleStringValidators.isValidPassword("pass")).isFalse();
    }

    @Test
    void isEmail() {
        Assertions.assertThat(simpleStringValidators.isEmail("email")).isFalse();
        Assertions.assertThat(simpleStringValidators.isEmail(null)).isFalse();
        Assertions.assertThat(simpleStringValidators.isEmail("email@something")).isTrue();
        Assertions.assertThat(simpleStringValidators.isEmail("email@something.com")).isTrue();
    }
}