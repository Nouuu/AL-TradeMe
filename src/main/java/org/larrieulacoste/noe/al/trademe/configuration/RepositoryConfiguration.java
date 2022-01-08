package org.larrieulacoste.noe.al.trademe.configuration;

import org.larrieulacoste.noe.al.trademe.features.invoices.domain.Invoices;
import org.larrieulacoste.noe.al.trademe.features.invoices.infrastructure.InMemoryInvoices;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Contractors;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesmen;
import org.larrieulacoste.noe.al.trademe.features.members.infrastructure.InMemoryContractors;
import org.larrieulacoste.noe.al.trademe.features.members.infrastructure.InMemoryTradesmen;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

@Dependent
final class RepositoryConfiguration {

    @Produces
    Tradesmen tradesmen() {
        return new InMemoryTradesmen();
    }

    @Produces
    Contractors contractors() {
        return new InMemoryContractors();
    }

    @Produces
    Invoices invoices() {
        return new InMemoryInvoices();
    }
}
