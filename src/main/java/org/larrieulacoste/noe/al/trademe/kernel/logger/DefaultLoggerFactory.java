package org.larrieulacoste.noe.al.trademe.kernel.logger;

import java.util.Objects;

public final class DefaultLoggerFactory implements LoggerFactory {

    @Override
    public Logger getLogger(Object object) {
        return new DefaultLogger(Objects.requireNonNull(object).getClass().getSimpleName());
    }
}
