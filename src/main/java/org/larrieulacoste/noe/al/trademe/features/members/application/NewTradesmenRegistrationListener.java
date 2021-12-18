package org.larrieulacoste.noe.al.trademe.features.members.application;

import org.larrieulacoste.noe.al.trademe.application.event.NewTradesmanRegistration;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

class NewTradesmenRegistrationListener implements EventSubscriber<NewTradesmanRegistration> {

    private final TradesmenService tradesmenService;

    public NewTradesmenRegistrationListener(TradesmenService tradesmenService) {
        this.tradesmenService = tradesmenService;
    }
    
    @Override
    public void accept(NewTradesmanRegistration event) {
        tradesmenService.newTradesman(event.getTradesmanRegistration());
    }
}
