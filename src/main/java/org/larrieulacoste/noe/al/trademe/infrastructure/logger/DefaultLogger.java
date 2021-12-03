package org.larrieulacoste.noe.al.trademe.infrastructure.logger;

import org.larrieulacoste.noe.al.trademe.domain.logger.Logger;

import java.util.Objects;
import java.util.logging.Level;

public class DefaultLogger implements Logger {
    private final java.util.logging.Logger logger;

    public DefaultLogger(String name) {
        this.logger = java.util.logging.Logger.getLogger(Objects.requireNonNull(name));
    }

    @Override
    public void log(String message) {
        if (logger.isLoggable(Level.INFO)) {
            logger.log(Level.INFO, String.format(" %s%n%s%n", logger.getName(), message));
        }
    }

    @Override
    public void error(String message) {
        if (logger.isLoggable(Level.SEVERE)) {
            logger.log(Level.SEVERE, String.format(" %s%n%s%n", logger.getName(), message));
        }
    }
}
