package org.larrieulacoste.noe.al.trademe.features.members.application;

import org.larrieulacoste.noe.al.trademe.application.event.TradesmanEventEntity;
import org.larrieulacoste.noe.al.trademe.application.event.TradesmanNewRegistration;
import org.larrieulacoste.noe.al.trademe.features.members.application.command.CreateTradesman;
import org.larrieulacoste.noe.al.trademe.features.members.kernel.MembersCommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

public final class NewTradesmenRegistrationListener implements EventSubscriber<TradesmanNewRegistration> {

    private final MembersCommandBus commandBus;

    public NewTradesmenRegistrationListener(MembersCommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public void accept(TradesmanNewRegistration event) {
        TradesmanEventEntity tradesman = event.tradesman;
        commandBus.send(new CreateTradesman(tradesman.firstname, tradesman.lastname, tradesman.email,
                tradesman.password, tradesman.paymentMethod.paymentMethodType.value, tradesman.paymentMethod.paymentInfo));
    }
}
