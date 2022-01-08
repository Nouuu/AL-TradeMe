package org.larrieulacoste.noe.al.trademe.features.members.application.command;

import org.larrieulacoste.noe.al.trademe.application.event.TradesmanEventEntity;
import org.larrieulacoste.noe.al.trademe.application.event.TradesmanUpdated;
import org.larrieulacoste.noe.al.trademe.application.event.TradesmenSubscriptionPendingPayment;
import org.larrieulacoste.noe.al.trademe.features.members.domain.SubscriptionStatus;
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

    PublishTradesmenPendingSubscriptionPaymentService(Tradesmen tradesmen, EventBus<ApplicationEvent> eventBus) {
        this.tradesmen = Objects.requireNonNull(tradesmen);
        this.eventBus = eventBus;
    }

    @Override
    public Void handle(PublishTradesmenPendingSubscriptionPayment command) {
        List<Tradesman> tradesmenPendingPayment = tradesmen.findAll();

        tradesmenPendingPayment.forEach(this::updateSubscriptionToPending);

        eventBus.publish(
                TradesmenSubscriptionPendingPayment.withTradesmen(
                        tradesmenPendingPayment.stream()
                                .map(tradesman -> TradesmanEventEntity.withEntityIdOnly(tradesman.getEntityId()))
                                .collect(Collectors.toList())
                )
        );
        return null;
    }

    public void updateSubscriptionToPending(Tradesman tradesman) {
        Tradesman updatedTradesman = Tradesman.of(
                tradesman.getEntityId(),
                tradesman.getLastname(),
                tradesman.getFirstname(),
                tradesman.getEmail(),
                tradesman.getPassword(),
                SubscriptionStatus.PENDING_PAYMENT,
                tradesman.getPaymentMethod()
        );
        tradesmen.save(updatedTradesman);
        eventBus.publish(TradesmanUpdated.withTradesman(TradesmanEventEntity.of(
                tradesman.getEntityId(),
                tradesman.getLastname().getField(),
                tradesman.getFirstname().getField(),
                tradesman.getEmail().getEmailAddressString(),
                tradesman.getPassword().getPasswordString(),
                tradesman.getPaymentMethod()
        )));
    }

}
