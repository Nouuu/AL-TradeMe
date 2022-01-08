package org.larrieulacoste.noe.al.trademe.kernel.logger;

import java.util.Objects;

public class DefaultLoggerFactory implements LoggerFactory {

    @Override
    public Logger getLogger(Class<?> objectClass) {
        return new DefaultLogger(Objects.requireNonNull(objectClass).getSimpleName());
    }
}