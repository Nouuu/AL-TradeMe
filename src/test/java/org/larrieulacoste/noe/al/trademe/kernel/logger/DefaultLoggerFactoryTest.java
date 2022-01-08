package org.larrieulacoste.noe.al.trademe.kernel.logger;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class DefaultLoggerFactoryTest {

    @Test
    void getLogger() {
        LoggerFactory loggerFactory = new DefaultLoggerFactory();
        Assertions.assertThat(loggerFactory.getLogger(DefaultLoggerFactoryTest.class))
                .isNotNull()
                .isInstanceOf(DefaultLogger.class);
    }
}