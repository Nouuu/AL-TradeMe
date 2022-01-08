package org.larrieulacoste.noe.al.trademe.features.members.kernel;

import org.larrieulacoste.noe.al.trademe.kernel.query.DefaultQueryBus;
import org.larrieulacoste.noe.al.trademe.kernel.query.Query;
import org.larrieulacoste.noe.al.trademe.kernel.query.QueryHandler;

import java.util.Map;

public final class DefaultMembersQueryBus extends DefaultQueryBus implements MembersQueryBus {
    public DefaultMembersQueryBus(Map<Class<? extends Query>, QueryHandler<? extends Query, ?>> queryMap) {
        super(queryMap);
    }
}
