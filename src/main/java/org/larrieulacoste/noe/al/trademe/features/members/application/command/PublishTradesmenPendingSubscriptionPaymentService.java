package org.larrieulacoste.noe.al.trademe.features.members.application.command;

import org.larrieulacoste.noe.al.trademe.application.event.TradesmanEventEntity;
import org.larrieulacoste.noe.al.trademe.application.event.TradesmanUpdated;
import org.larrieulacoste.noe.al.trademe.application.event.TradesmenSubscriptionPendingPayment;
import org.larrieulacoste.noe.al.trademe.features.members.domain.SubscriptionStatus;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesman;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesmans;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ApplicationScoped
public class PublishTradesmenPendingSubscriptionPaymentService
        implements CommandHandler<PublishTradesmenPendingSubscriptionPayment, Void> {
    private final Tradesmans tradesmans;
    private final EventBus<ApplicationEvent> eventBus;

    PublishTradesmenPendingSubscriptionPaymentService(Tradesmans tradesmans, EventBus<ApplicationEvent> eventBus) {
        this.tradesmans = Objects.requireNonNull(tradesmans);
        this.eventBus = eventBus;
    }

    @Override
    public Void handle(PublishTradesmenPendingSubscriptionPayment command) {
        List<Tradesman> tradesmenPendingPayment = tradesmans.findAll();

        tradesmenPendingPayment.forEach(this::updateSubscriptionToPending);

        eventBus.publish(
                TradesmenSubscriptionPendingPayment.withTradesmen(
                        tradesmenPendingPayment.stream()
                                .map(tradesman -> TradesmanEventEntity.withEntityIdOnly(tradesman.entityId))
                                .collect(Collectors.toList())));
        return null;
    }

    public void updateSubscriptionToPending(Tradesman tradesman) {
        Tradesman updatedTradesman = Tradesman.of(
                tradesman.entityId,
                tradesman.lastname,
                tradesman.firstname,
                tradesman.email,
                tradesman.password,
                SubscriptionStatus.PENDING_PAYMENT,
                tradesman.paymentMethod);
        tradesmans.save(updatedTradesman);
        eventBus.publish(TradesmanUpdated.withTradesman(TradesmanEventEntity.of(
                tradesman.entityId,
                tradesman.lastname.value,
                tradesman.firstname.value,
                tradesman.email.value,
                tradesman.password.value,
                tradesman.paymentMethod)));
    }

}
