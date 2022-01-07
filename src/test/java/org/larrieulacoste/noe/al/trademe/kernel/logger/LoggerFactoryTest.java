package org.larrieulacoste.noe.al.trademe.kernel.logger;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class LoggerFactoryTest {

    @Test
    void getLogger() {
        final Logger logger = LoggerFactory.getLoggerStatic(this);
        Assertions.assertThat(logger).isInstanceOf(DefaultLogger.class);
    }
}