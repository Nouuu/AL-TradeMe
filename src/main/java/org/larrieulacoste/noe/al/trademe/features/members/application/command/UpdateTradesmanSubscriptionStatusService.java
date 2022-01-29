package org.larrieulacoste.noe.al.trademe.features.members.application.command;

import org.larrieulacoste.noe.al.trademe.application.event.TradesmanEventEntity;
import org.larrieulacoste.noe.al.trademe.application.event.TradesmanUpdated;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesman;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesmans;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;

import javax.enterprise.context.ApplicationScoped;
import java.util.Objects;

@ApplicationScoped
public class UpdateTradesmanSubscriptionStatusService
        implements CommandHandler<UpdateTradesmanSubscriptionStatus, Void> {
    private final Tradesmans tradesmans;
    private final EventBus<ApplicationEvent> eventBus;

    UpdateTradesmanSubscriptionStatusService(Tradesmans tradesmans, EventBus<ApplicationEvent> eventBus) {
        this.tradesmans = Objects.requireNonNull(tradesmans);
        this.eventBus = eventBus;
    }

    @Override
    public Void handle(UpdateTradesmanSubscriptionStatus command) {
        Tradesman tradesman = tradesmans.byId(command.tradesmanId);

        Tradesman updatedTradesman = Tradesman.of(
                tradesman.entityId,
                tradesman.lastname,
                tradesman.firstname,
                tradesman.email,
                tradesman.password,
                command.subscriptionStatus,
                tradesman.paymentMethod);

        tradesmans.save(updatedTradesman);
        eventBus.publish(TradesmanUpdated.withTradesman(TradesmanEventEntity.of(
                tradesman.entityId,
                tradesman.lastname.value,
                tradesman.firstname.value,
                tradesman.email.value,
                tradesman.password.value,
                tradesman.paymentMethod)));
        return null;
    }
}
