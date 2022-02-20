package org.larrieulacoste.noe.al.trademe.features.members.application;

import org.larrieulacoste.noe.al.trademe.application.event.TradesmanEventEntity;
import org.larrieulacoste.noe.al.trademe.application.event.TradesmanNewRegistration;
import org.larrieulacoste.noe.al.trademe.features.members.application.command.CreateTradesman;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

public final class NewTradesmenRegistrationListener implements EventSubscriber<TradesmanNewRegistration> {

    private final CommandBus commandBus;

    public NewTradesmenRegistrationListener(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public void accept(TradesmanNewRegistration event) {
        TradesmanEventEntity tradesman = event.tradesman();
        var abilities = tradesman.professionalAblilites();
        var addressCoordinate = abilities.address().coordinate();

        commandBus.send(new CreateTradesman(
                tradesman.firstname(),
                tradesman.lastname(),
                tradesman.email(),
                tradesman.password(),
                tradesman.paymentMethod().paymentMethodType().value,
                tradesman.paymentMethod().paymentInfo(),
                abilities.profession().professionName().value,
                addressCoordinate.longitude(),
                addressCoordinate.latitude(),
                abilities.activityRadius().activityRadius(),
                abilities.dailyRate().amount().value(),
                abilities.address().locationName().value));
    }
}
