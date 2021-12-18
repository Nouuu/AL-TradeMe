package org.larrieulacoste.noe.al.trademe.features.members.infrastructure.kernel.query;

import java.util.Map;
import java.util.Objects;

public class DefaultQueryBus implements QueryBus {
    private final Map<Class<? extends Query>, QueryHandler<? extends Query, ?>> queryMap;

    public DefaultQueryBus(Map<Class<? extends Query>, QueryHandler<? extends Query, ?>> queryMap) {
        this.queryMap = Objects.requireNonNull(queryMap);
    }

    @SuppressWarnings("all")
    @Override
    public <Q extends Query, R> R send(Q query) {
        final QueryHandler<Q, R> queryHandler = (QueryHandler<Q, R>) queryMap.get(Objects.requireNonNull(query).getClass());
        if (queryHandler == null) {
            throw new RuntimeException("No such query handler for " + query.getClass().getName());
        }
        return queryHandler.handle(query);
    }
}
