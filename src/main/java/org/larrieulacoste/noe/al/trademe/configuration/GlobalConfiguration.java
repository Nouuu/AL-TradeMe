package org.larrieulacoste.noe.al.trademe.configuration;

import org.larrieulacoste.noe.al.trademe.kernel.logger.LoggerFactory;
import org.larrieulacoste.noe.al.trademe.kernel.logger.DefaultLoggerFactory;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GlobalConfiguration {

    @ApplicationScoped
    LoggerFactory loggerFactory() {
        return new DefaultLoggerFactory();
    }
}
