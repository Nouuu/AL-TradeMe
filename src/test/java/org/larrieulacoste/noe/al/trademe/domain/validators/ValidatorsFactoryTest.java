package org.larrieulacoste.noe.al.trademe.domain.validators;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.larrieulacoste.noe.al.trademe.infrastructure.validators.SimpleDateValidators;
import org.larrieulacoste.noe.al.trademe.infrastructure.validators.SimpleStringValidators;

class ValidatorsFactoryTest {

    @Test
    void getDateValidatorsInstance() {
        Assertions.assertThat(ValidatorsFactory.getDateValidatorsInstance())
                .isNotNull()
                .isInstanceOf(SimpleDateValidators.class);
    }

    @Test
    void getStringValidatorsInstance() {
        Assertions.assertThat(ValidatorsFactory.getStringValidatorsInstance())
                .isNotNull()
                .isInstanceOf(SimpleStringValidators.class);
    }
}