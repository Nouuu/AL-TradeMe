package org.larrieulacoste.noe.al.trademe.configuration;

import org.larrieulacoste.noe.al.trademe.domain.logger.LoggerFactory;
import org.larrieulacoste.noe.al.trademe.infrastructure.logger.DefaultLoggerFactory;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GlobalConfiguration {

    @ApplicationScoped
    LoggerFactory loggerFactory() {
        return new DefaultLoggerFactory();
    }
}
