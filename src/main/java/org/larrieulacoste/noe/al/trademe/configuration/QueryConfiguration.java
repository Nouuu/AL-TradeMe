package org.larrieulacoste.noe.al.trademe.configuration;

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

    @ApplicationScoped
    QueryBus queryBus() {
        Map<Class<? extends Query>, QueryHandler<? extends Query, ?>> queryMap = new HashMap<>();

        // Members feature
        queryMap.put(RetrieveContractorById.class, retrieveContractorByIdService);
        queryMap.put(RetrieveContractors.class, retrieveContractorsService);
        queryMap.put(RetrieveTradesmanById.class, retrieveTradesmanByIdService);
        queryMap.put(RetrieveTradesmen.class, retrieveTradesmenService);

        return new DefaultQueryBus(queryMap);
    }

}
