package org.larrieulacoste.noe.al.trademe.domain.validators;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.larrieulacoste.noe.al.trademe.kernel.validators.SimpleDateValidators;
import org.larrieulacoste.noe.al.trademe.kernel.validators.SimpleStringValidators;
import org.larrieulacoste.noe.al.trademe.kernel.validators.ValidatorsAccessor;

class ValidatorsFactoryTest {

    @Test
    void getDateValidatorsInstance() {
        Assertions.assertThat(ValidatorsAccessor.getDateValidatorsInstance())
                .isNotNull()
                .isInstanceOf(SimpleDateValidators.class);
    }

    @Test
    void getStringValidatorsInstance() {
        Assertions.assertThat(ValidatorsAccessor.getStringValidatorsInstance())
                .isNotNull()
                .isInstanceOf(SimpleStringValidators.class);
    }
}