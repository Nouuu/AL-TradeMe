package org.larrieulacoste.noe.al.trademe.kernel.logger;

import java.util.Objects;

public final class JBossLoggerFactory implements LoggerFactory {

    @Override
    public Logger getLogger(Class<?> objectClass) {
        return new JBossLogger(Objects.requireNonNull(objectClass).getSimpleName());
    }
}