package org.larrieulacoste.noe.al.trademe.features.members.application.command;

import org.larrieulacoste.noe.al.trademe.domain.event.TradesmanUpdated;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesman;
import org.larrieulacoste.noe.al.trademe.features.members.domain.TradesmanBuilder;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesmen;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;

import javax.enterprise.context.ApplicationScoped;
import java.util.Objects;

@ApplicationScoped
public class UpdateTradesmanSubscriptionStatusService
        implements CommandHandler<UpdateTradesmanSubscriptionStatus, Void> {
    private final Tradesmen tradesmen;
    private final EventBus<ApplicationEvent> eventBus;
    private final TradesmanBuilder tradesmanBuilder;

    UpdateTradesmanSubscriptionStatusService(Tradesmen tradesmen, EventBus<ApplicationEvent> eventBus,
                                             TradesmanBuilder tradesmanBuilder) {
        this.tradesmen = Objects.requireNonNull(tradesmen);
        this.eventBus = eventBus;
        this.tradesmanBuilder = Objects.requireNonNull(tradesmanBuilder);
    }

    @Override
    public Void handle(UpdateTradesmanSubscriptionStatus command) {
        Tradesman tradesman = tradesmen.byId(command.tradesmanId());
        tradesmanBuilder.clear();
        tradesmanBuilder.withTradesman(tradesman);
        Tradesman updatedTradesman = tradesmanBuilder.withSubscriptionStatus(command.subscriptionStatus())
                .build(tradesman.entityId());

        tradesmen.save(updatedTradesman);
        eventBus.publish(TradesmanUpdated.of(
                updatedTradesman.entityId(),
                updatedTradesman.firstname().value(),
                updatedTradesman.lastname().value(),
                updatedTradesman.email().value(),
                updatedTradesman.paymentMethod(),
                updatedTradesman.address(),
                updatedTradesman.professionalAbilities()
        ));
        return null;
    }
}
