package org.larrieulacoste.noe.al.trademe.kernel.logger;

import java.util.Objects;

public interface LoggerFactory {

    static Logger getLoggerStatic(Object object) {
        return new DefaultLogger(Objects.requireNonNull(object).getClass().getSimpleName());
    }

    Logger getLogger(Class<?> objectClass);

}
