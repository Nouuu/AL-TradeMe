package org.larrieulacoste.noe.al.trademe.infrastructure.logger;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.larrieulacoste.noe.al.trademe.kernel.logger.Logger;
import org.larrieulacoste.noe.al.trademe.kernel.logger.DefaultLogger;
import org.larrieulacoste.noe.al.trademe.kernel.logger.DefaultLoggerFactory;

class DefaultLoggerFactoryTest {
    private final DefaultLoggerFactory defaultLoggerFactory = new DefaultLoggerFactory();

    @Test
    void getLogger() {
        final Logger logger = defaultLoggerFactory.getLogger(this);
        Assertions.assertThat(logger).isInstanceOf(DefaultLogger.class);
    }
}