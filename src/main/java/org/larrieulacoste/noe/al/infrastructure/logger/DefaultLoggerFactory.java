package org.larrieulacoste.noe.al.infrastructure.logger;

import org.larrieulacoste.noe.al.domain.logger.Logger;
import org.larrieulacoste.noe.al.domain.logger.LoggerFactory;

import java.util.Objects;

public final class DefaultLoggerFactory implements LoggerFactory {

    @Override
    public Logger getLogger(Object object) {
        return new DefaultLogger(Objects.requireNonNull(object).getClass().getName());
    }
}
