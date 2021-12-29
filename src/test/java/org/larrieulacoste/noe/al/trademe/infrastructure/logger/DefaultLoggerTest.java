package org.larrieulacoste.noe.al.trademe.infrastructure.logger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.larrieulacoste.noe.al.trademe.kernel.logger.DefaultLogger;

class DefaultLoggerTest {
    private final DefaultLogger defaultLogger = new DefaultLogger("Logger");

    @Test()
    void log() {
        Assertions.assertAll(() -> defaultLogger.log("log message"));
    }

    @Test
    void error() {
        Assertions.assertAll(() -> defaultLogger.error("error message"));
    }
}