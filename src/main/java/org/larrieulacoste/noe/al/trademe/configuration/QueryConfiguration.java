package org.larrieulacoste.noe.al.trademe.configuration;

import org.larrieulacoste.noe.al.trademe.features.invoices.application.query.*;
import org.larrieulacoste.noe.al.trademe.features.members.application.query.*;
import org.larrieulacoste.noe.al.trademe.features.projects.application.query.*;
import org.larrieulacoste.noe.al.trademe.kernel.query.DefaultQueryBus;
import org.larrieulacoste.noe.al.trademe.kernel.query.Query;
import org.larrieulacoste.noe.al.trademe.kernel.query.QueryBus;
import org.larrieulacoste.noe.al.trademe.kernel.query.QueryHandler;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Dependent
final class QueryConfiguration {
    @Inject
    RetrieveContractorByIdService retrieveContractorByIdService;
    @Inject
    RetrieveContractorsService retrieveContractorsService;
    @Inject
    RetrieveTradesmanByIdService retrieveTradesmanByIdService;
    @Inject
    RetrieveTradesmenService retrieveTradesmenService;
    @Inject
    MatchTradesmenService matchTradesmenService;
    @Inject
    RetrieveTradesmanSkillsService retrieveTradesmanSkillsService;

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

    @Inject
    RetrieveContractorProjectsService retrieveContractorProjectsService;
    @Inject
    RetrieveProjectByIdService retrieveProjectByIdService;
    @Inject
    RetrieveProjectsService retrieveProjectsService;
    @Inject
    RetrieveTradesmanProjectsService retrieveTradesmanProjectsService;
    @Inject
    RetrieveProjectSkillsService retrieveProjectSkillsService;

    @Produces
    @Singleton
    QueryBus queryBus() {
        Map<Class<? extends Query>, QueryHandler<? extends Query, ?>> queryMap = new HashMap<>();

        // Invoices
        queryMap.put(RetrieveAllInvoices.class, retrieveAllInvoicesService);
        queryMap.put(RetrieveContractorsInvoices.class, retrieveContractorsInvoicesService);
        queryMap.put(RetrieveContractorInvoices.class, retrieveContractorInvoicesService);
        queryMap.put(RetrieveTradesmenInvoices.class, retrieveTradesmenInvoicesService);
        queryMap.put(RetrieveTradesmanInvoices.class, retrieveTradesmanInvoicesService);
        queryMap.put(RetrieveInvoiceById.class, retrieveInvoiceByIdService);

        // Members
        queryMap.put(RetrieveContractorById.class, retrieveContractorByIdService);
        queryMap.put(RetrieveContractors.class, retrieveContractorsService);
        queryMap.put(RetrieveTradesmanById.class, retrieveTradesmanByIdService);
        queryMap.put(RetrieveTradesmen.class, retrieveTradesmenService);
        queryMap.put(MatchTradesmen.class, matchTradesmenService);
        queryMap.put(RetrieveTradesmanSkills.class, retrieveTradesmanSkillsService);

        // Payments

        // Projects
        queryMap.put(RetrieveContractorProjects.class, retrieveContractorProjectsService);
        queryMap.put(RetrieveProjectById.class, retrieveProjectByIdService);
        queryMap.put(RetrieveProjects.class, retrieveProjectsService);
        queryMap.put(RetrieveTradesmanProjects.class, retrieveTradesmanProjectsService);
        queryMap.put(RetrieveProjectSkills.class, retrieveProjectSkillsService);

        return new DefaultQueryBus(queryMap);
    }
}