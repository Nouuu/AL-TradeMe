package org.larrieulacoste.noe.al.trademe.kernel.logger;

import java.util.Objects;
import java.util.logging.Level;

final class DefaultLogger implements Logger {
    private final java.util.logging.Logger logger;

    public DefaultLogger(String name) {
        this.logger = java.util.logging.Logger.getLogger(Objects.requireNonNull(name));
    }

    @Override
    public void log(String message) {
        if (logger.isLoggable(Level.INFO)) {
            logger.log(Level.INFO, String.format("%n%s%n%s%n", logger.getName(), message));
        }
    }

    @Override
    public void error(String message) {
        if (logger.isLoggable(Level.SEVERE)) {
            logger.log(Level.SEVERE, String.format("%n%s%n%s%n", logger.getName(), message));
        }
    }
}
