package org.larrieulacoste.noe.al.trademe.kernel.logger;

import java.util.Objects;

final class JBossLogger implements Logger {
    private final org.jboss.logging.Logger logger;

    public JBossLogger(String name) {
        this.logger = org.jboss.logging.Logger.getLogger(Objects.requireNonNull(name));
    }

    @Override
    public void log(String message) {
        logger.info(String.format("%n%s%n%s%n", logger.getName(), message));
    }

    @Override
    public void error(String message) {
        logger.error(String.format("%n%s%n%s%n", logger.getName(), message));
    }
}
