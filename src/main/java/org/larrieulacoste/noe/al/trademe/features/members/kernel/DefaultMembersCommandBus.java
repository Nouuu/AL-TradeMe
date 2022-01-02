package org.larrieulacoste.noe.al.trademe.features.members.kernel;

import org.larrieulacoste.noe.al.trademe.kernel.command.Command;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.command.DefaultCommandBus;

import java.util.Map;

public class DefaultMembersCommandBus extends DefaultCommandBus implements MembersCommandBus {
    public DefaultMembersCommandBus(Map<Class<? extends Command>, CommandHandler<? extends Command, ?>> commandMap) {
        super(commandMap);
    }
}
