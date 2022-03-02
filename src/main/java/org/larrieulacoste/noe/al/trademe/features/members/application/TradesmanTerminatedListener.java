package org.larrieulacoste.noe.al.trademe.features.members.application;

import org.larrieulacoste.noe.al.trademe.domain.event.TradesmanTerminated;
import org.larrieulacoste.noe.al.trademe.features.members.application.command.ReleaseTradesman;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

public class TradesmanTerminatedListener implements EventSubscriber<TradesmanTerminated> {
    private final CommandBus commandBus;

    public TradesmanTerminatedListener(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public void accept(TradesmanTerminated event) {
        commandBus.send(new ReleaseTradesman(event.tradesmanId(), event.startDate(), event.endDate()));
    }
}
