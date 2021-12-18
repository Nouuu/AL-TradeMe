package org.larrieulacoste.noe.al.trademe.features.members.application;

import org.larrieulacoste.noe.al.trademe.application.event.NewTradesmanRegistration;
import org.larrieulacoste.noe.al.trademe.application.event.TradesmanEventEntity;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

class NewTradesmenRegistrationListener implements EventSubscriber<NewTradesmanRegistration> {

    private final CommandBus commandBus;

    public NewTradesmenRegistrationListener(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public void accept(NewTradesmanRegistration event) {
        TradesmanEventEntity tradesman = event.getTradesmanRegistration();
        commandBus.send(new CreateTradesman(tradesman.firstname, tradesman.lastname, tradesman.email, tradesman.password));
    }
}
