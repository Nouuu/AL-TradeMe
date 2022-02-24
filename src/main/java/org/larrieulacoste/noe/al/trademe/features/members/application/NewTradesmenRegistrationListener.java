package org.larrieulacoste.noe.al.trademe.features.members.application;

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
        var abilities = event.professionalAbilities();
        var addressCoordinate = event.address().coordinate();

        commandBus.send(new CreateTradesman(
                event.firstname(),
                event.lastname(),
                event.email(),
                event.password(),
                event.paymentMethod().paymentMethodType().value,
                event.paymentMethod().paymentInfo(),
                abilities.profession().professionName().value(),
                addressCoordinate.longitude(),
                addressCoordinate.latitude(),
                abilities.activityRadius().activityRadius(),
                abilities.dailyRate().amount().value(),
                event.address().locationName().value()));
    }
}
