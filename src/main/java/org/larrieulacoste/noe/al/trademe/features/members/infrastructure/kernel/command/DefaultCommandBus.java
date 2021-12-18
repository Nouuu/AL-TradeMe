package org.larrieulacoste.noe.al.trademe.features.members.infrastructure.kernel.command;

import java.util.Map;
import java.util.Objects;

public class DefaultCommandBus implements CommandBus {
    private final Map<Class<? extends Command>, CommandHandler<? extends Command, ?>> queryMap;

    public DefaultCommandBus(Map<Class<? extends Command>, CommandHandler<? extends Command, ?>> queryMap) {
        this.queryMap = Objects.requireNonNull(queryMap);
    }

    @SuppressWarnings("all")
    @Override
    public <Q extends Command, R> R send(Q query) {
        final CommandHandler<Q, R> queryHandler = (CommandHandler<Q, R>) queryMap.get(Objects.requireNonNull(query).getClass());
        if (queryHandler == null) {
            throw new RuntimeException("No such command handler for " + query.getClass().getName());
        }
        return queryHandler.handle(query);
    }
}
