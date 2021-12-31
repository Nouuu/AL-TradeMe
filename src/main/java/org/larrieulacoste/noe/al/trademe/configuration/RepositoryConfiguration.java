package org.larrieulacoste.noe.al.trademe.configuration;

import org.larrieulacoste.noe.al.trademe.features.invoices.domain.Invoices;
import org.larrieulacoste.noe.al.trademe.features.invoices.infrastructure.InMemoryInvoices;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Contractors;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesmen;
import org.larrieulacoste.noe.al.trademe.features.members.infrastructure.InMemoryContractors;
import org.larrieulacoste.noe.al.trademe.features.members.infrastructure.InMemoryTradesmen;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RepositoryConfiguration {

    @ApplicationScoped
    Tradesmen tradesmen() {
        return new InMemoryTradesmen();
    }

    @ApplicationScoped
    Contractors contractors() {
        return new InMemoryContractors();
    }

    @ApplicationScoped
    Invoices invoices() {
        return new InMemoryInvoices();
    }
}
