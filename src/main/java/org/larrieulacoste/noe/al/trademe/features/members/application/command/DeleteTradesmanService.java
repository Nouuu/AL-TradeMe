package org.larrieulacoste.noe.al.trademe.features.members.application.command;

import org.larrieulacoste.noe.al.trademe.domain.event.TradesmanDeleted;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesman;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesmen;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.EntityId;

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
        eventBus.publish(TradesmanDeleted.of(tradesman.entityId()));
        return null;
    }
}
