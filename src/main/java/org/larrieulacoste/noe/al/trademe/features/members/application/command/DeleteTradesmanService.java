package org.larrieulacoste.noe.al.trademe.features.members.application.command;

import org.larrieulacoste.noe.al.trademe.application.event.TradesmanDeleted;
import org.larrieulacoste.noe.al.trademe.application.event.TradesmanEventEntity;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesman;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesmen;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;

import javax.enterprise.context.ApplicationScoped;
import java.util.Objects;

@ApplicationScoped
public class DeleteTradesmanService implements CommandHandler<DeleteTradesman, Void> {
    private final Tradesmen tradesmen;
    private final EventBus<ApplicationEvent> eventBus;

    DeleteTradesmanService(Tradesmen tradesmen, EventBus<ApplicationEvent> eventBus) {
        this.tradesmen = Objects.requireNonNull(tradesmen);
        this.eventBus = eventBus;
    }

    @Override
    public Void handle(DeleteTradesman command) {
        Tradesman tradesman = tradesmen.byId(EntityId.of(command.tradesmanId()));
        tradesmen.remove(tradesman);
        eventBus.publish(TradesmanDeleted.withTradesman(
                TradesmanEventEntity.withEntityIdOnly(tradesman.entityId())
        ));
        return null;
    }
}
