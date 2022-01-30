package org.larrieulacoste.noe.al.trademe.features.members.application.command;

import org.larrieulacoste.noe.al.trademe.application.event.TradesmanDeleted;
import org.larrieulacoste.noe.al.trademe.application.event.TradesmanEventEntity;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesman;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesmans;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;

import javax.enterprise.context.ApplicationScoped;
import java.util.Objects;

@ApplicationScoped
public class DeleteTradesmanService implements CommandHandler<DeleteTradesman, Void> {
    private final Tradesmans tradesmans;
    private final EventBus<ApplicationEvent> eventBus;

    DeleteTradesmanService(Tradesmans tradesmans, EventBus<ApplicationEvent> eventBus) {
        this.tradesmans = Objects.requireNonNull(tradesmans);
        this.eventBus = eventBus;
    }

    @Override
    public Void handle(DeleteTradesman command) {
        Tradesman tradesman = tradesmans.byId(EntityId.of(command.tradesmanId));
        tradesmans.remove(tradesman);
        eventBus.publish(TradesmanDeleted.withTradesman(
                TradesmanEventEntity.withEntityIdOnly(tradesman.entityId)));
        return null;
    }
}
