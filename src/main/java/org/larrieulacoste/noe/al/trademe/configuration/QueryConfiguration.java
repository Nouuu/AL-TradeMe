package org.larrieulacoste.noe.al.trademe.configuration;

import org.larrieulacoste.noe.al.trademe.features.invoices.application.query.*;
import org.larrieulacoste.noe.al.trademe.features.members.application.query.*;
import org.larrieulacoste.noe.al.trademe.kernel.query.DefaultQueryBus;
import org.larrieulacoste.noe.al.trademe.kernel.query.Query;
import org.larrieulacoste.noe.al.trademe.kernel.query.QueryBus;
import org.larrieulacoste.noe.al.trademe.kernel.query.QueryHandler;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class QueryConfiguration {
    @Inject
    RetrieveContractorByIdService retrieveContractorByIdService;
    @Inject
    RetrieveContractorsService retrieveContractorsService;
    @Inject
    RetrieveTradesmanByIdService retrieveTradesmanByIdService;
    @Inject
    RetrieveTradesmenService retrieveTradesmenService;

    @Inject
    RetrieveAllInvoicesService retrieveAllInvoicesService;
    @Inject
    RetrieveContractorsInvoicesService retrieveContractorsInvoicesService;
    @Inject
    RetrieveContractorInvoicesService retrieveContractorInvoicesService;
    @Inject
    RetrieveTradesmenInvoicesService retrieveTradesmenInvoicesService;
    @Inject
    RetrieveTradesmanInvoicesService retrieveTradesmanInvoicesService;
    @Inject
    RetrieveInvoiceByIdService retrieveInvoiceByIdService;

    @ApplicationScoped
    QueryBus queryBus() {
        Map<Class<? extends Query>, QueryHandler<? extends Query, ?>> queryMap = new HashMap<>();

        // Members feature
        queryMap.put(RetrieveContractorById.class, retrieveContractorByIdService);
        queryMap.put(RetrieveContractors.class, retrieveContractorsService);
        queryMap.put(RetrieveTradesmanById.class, retrieveTradesmanByIdService);
        queryMap.put(RetrieveTradesmen.class, retrieveTradesmenService);

        // Invoices feature
        queryMap.put(RetrieveAllInvoices.class, retrieveAllInvoicesService);
        queryMap.put(RetrieveContractorsInvoices.class, retrieveContractorsInvoicesService);
        queryMap.put(RetrieveContractorInvoices.class, retrieveContractorInvoicesService);
        queryMap.put(RetrieveTradesmenInvoices.class, retrieveTradesmenInvoicesService);
        queryMap.put(RetrieveTradesmanInvoices.class, retrieveTradesmanInvoicesService);
        queryMap.put(RetrieveInvoiceById.class, retrieveInvoiceByIdService);

        return new DefaultQueryBus(queryMap);
    }

}
