package org.larrieulacoste.noe.al.trademe.configuration;

import org.larrieulacoste.noe.al.trademe.features.invoices.domain.Invoices;
import org.larrieulacoste.noe.al.trademe.features.invoices.infrastructure.InMemoryInvoices;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Contractors;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesmen;
import org.larrieulacoste.noe.al.trademe.features.members.infrastructure.InMemoryContractors;
import org.larrieulacoste.noe.al.trademe.features.members.infrastructure.InMemoryTradesmen;
import org.larrieulacoste.noe.al.trademe.kernel.logger.Logger;
import org.larrieulacoste.noe.al.trademe.kernel.logger.LoggerQualifier;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@Dependent
final class RepositoryConfiguration {

    @Inject
    @LoggerQualifier(Tradesmen.class)

    Logger tradesmenLogger;
    @Inject
    @LoggerQualifier(Contractors.class)
    Logger contractorsLogger;

    @Inject
    @LoggerQualifier(Invoices.class)
    Logger invoicesLogger;

    @Produces
    Tradesmen tradesmen() {
        return new InMemoryTradesmen(tradesmenLogger);
    }

    @Produces
    Contractors contractors() {
        return new InMemoryContractors(contractorsLogger);
    }

    @Produces
    Invoices invoices() {
        return new InMemoryInvoices(invoicesLogger);
    }
}
