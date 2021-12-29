package org.larrieulacoste.noe.al.trademe.kernel.command;

import java.util.Map;
import java.util.Objects;

public class DefaultCommandBus implements CommandBus {
    private final Map<Class<? extends Command>, CommandHandler<? extends Command, ?>> commandMap;

    public DefaultCommandBus(Map<Class<? extends Command>, CommandHandler<? extends Command, ?>> commandMap) {
        this.commandMap = Objects.requireNonNull(commandMap);
    }

    @SuppressWarnings("all")
    @Override
    public <Q extends Command, R> R send(Q command) {
        final CommandHandler<Q, R> queryHandler = (CommandHandler<Q, R>) commandMap.get(Objects.requireNonNull(command).getClass());
        if (queryHandler == null) {
            throw new RuntimeException("No such command handler for " + command.getClass().getName());
        }
        return queryHandler.handle(command);
    }
}
