package org.larrieulacoste.noe.al.trademe.configuration;

import org.larrieulacoste.noe.al.trademe.kernel.logger.LoggerFactory;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Contractors;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesmen;
import org.larrieulacoste.noe.al.trademe.features.members.infrastructure.InMemoryContractors;
import org.larrieulacoste.noe.al.trademe.features.members.infrastructure.InMemoryTradesmen;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class RepositoryConfiguration {
    @Inject
    LoggerFactory loggerFactory;

    @ApplicationScoped
    Tradesmen tradesmen() {
        return new InMemoryTradesmen(loggerFactory);
    }

    @ApplicationScoped
    Contractors contractors() {
        return new InMemoryContractors(loggerFactory);
    }
}
