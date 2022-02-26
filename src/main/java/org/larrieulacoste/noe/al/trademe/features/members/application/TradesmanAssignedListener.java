package org.larrieulacoste.noe.al.trademe.features.members.application;

import org.larrieulacoste.noe.al.trademe.application.event.TradesmanAssigned;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

public class TradesmanAssignedListener  implements EventSubscriber<TradesmanAssigned> {
    private final CommandBus commandBus;

    public TradesmanAssignedListener(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public void accept(TradesmanAssigned event) {
        commandBus.send()
    }
}
