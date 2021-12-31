package org.larrieulacoste.noe.al.trademe.features.members.application.command;

import org.larrieulacoste.noe.al.trademe.application.event.TradesmanEventEntity;
import org.larrieulacoste.noe.al.trademe.application.event.TradesmenSubscriptionPendingPayment;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesman;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesmen;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ApplicationScoped
public class PublishTradesmenPendingSubscriptionPaymentService implements CommandHandler<PublishTradesmenPendingSubscriptionPayment, Void> {
    private final Tradesmen tradesmen;
    private final EventBus<ApplicationEvent> eventBus;

    public PublishTradesmenPendingSubscriptionPaymentService(Tradesmen tradesmen, EventBus<ApplicationEvent> eventBus) {
        this.tradesmen = Objects.requireNonNull(tradesmen);
        this.eventBus = eventBus;
    }

    @Override
    public Void handle(PublishTradesmenPendingSubscriptionPayment command) {
        List<Tradesman> tradesmenPendingPayment = tradesmen.findAll(); // TODO
        eventBus.publish(
                TradesmenSubscriptionPendingPayment.withTradesmen(
                        tradesmenPendingPayment.stream()
                                .map(tradesman -> TradesmanEventEntity.withEntityIdOnly(tradesman.getEntityId()))
                                .collect(Collectors.toList())
                )
        );
        return null;
    }
}
