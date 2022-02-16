package org.larrieulacoste.noe.al.trademe.features.members.application.command;

import org.larrieulacoste.noe.al.trademe.application.event.TradesmanEventEntity;
import org.larrieulacoste.noe.al.trademe.application.event.TradesmanUpdated;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesman;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesmen;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;

import javax.enterprise.context.ApplicationScoped;
import java.util.Objects;

@ApplicationScoped
public class UpdateTradesmanSubscriptionStatusService implements CommandHandler<UpdateTradesmanSubscriptionStatus, Void> {
    private final Tradesmen tradesmen;
    private final EventBus<ApplicationEvent> eventBus;

    UpdateTradesmanSubscriptionStatusService(Tradesmen tradesmen, EventBus<ApplicationEvent> eventBus) {
        this.tradesmen = Objects.requireNonNull(tradesmen);
        this.eventBus = eventBus;
    }

    @Override
    public Void handle(UpdateTradesmanSubscriptionStatus command) {
        Tradesman tradesman = tradesmen.byId(command.tradesmanId());

        Tradesman updatedTradesman = Tradesman.of(
                tradesman.entityId(),
                tradesman.lastname(),
                tradesman.firstname(),
                tradesman.email(),
                tradesman.password(),
                command.subscriptionStatus(),
                tradesman.paymentMethod(),
                tradesman.professionalAbilities(),
                tradesman.projects()
        );

        tradesmen.save(updatedTradesman);
        eventBus.publish(TradesmanUpdated.withTradesman(TradesmanEventEntity.of(
                tradesman.entityId(),
                tradesman.lastname().value,
                tradesman.firstname().value,
                tradesman.email().value,
                tradesman.password().value,
                tradesman.paymentMethod(),
                tradesman.professionalAbilities(),
                tradesman.projects()
        )));
        return null;
    }
}
