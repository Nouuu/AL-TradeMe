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
        Tradesman tradesman = tradesmen.byId(command.tradesmanId);

        Tradesman updatedTradesman = Tradesman.of(
                tradesman.getEntityId(),
                tradesman.getLastname(),
                tradesman.getFirstname(),
                tradesman.getEmail(),
                tradesman.getPassword(),
                command.subscriptionStatus,
                tradesman.getPaymentMethod()
        );

        tradesmen.save(updatedTradesman);
        eventBus.publish(TradesmanUpdated.withTradesman(TradesmanEventEntity.of(
                tradesman.getEntityId(),
                tradesman.getLastname().value,
                tradesman.getFirstname().value,
                tradesman.getEmail().value,
                tradesman.getPassword().value,
                tradesman.getPaymentMethod()
        )));
        return null;
    }
}
