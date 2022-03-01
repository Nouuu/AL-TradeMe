package org.larrieulacoste.noe.al.trademe.features.members.application.command;

import org.larrieulacoste.noe.al.trademe.domain.event.TradesmanUpdated;
import org.larrieulacoste.noe.al.trademe.domain.event.TradesmenSubscriptionPendingPayment;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.MemberPayment;
import org.larrieulacoste.noe.al.trademe.features.members.domain.SubscriptionStatus;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesman;
import org.larrieulacoste.noe.al.trademe.features.members.domain.TradesmanBuilder;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesmen;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class PublishTradesmenPendingSubscriptionPaymentService
        implements CommandHandler<PublishTradesmenPendingSubscriptionPayment, Void> {
    private final Tradesmen tradesmen;
    private final EventBus<ApplicationEvent> eventBus;
    private final TradesmanBuilder tradesmanBuilder;

    PublishTradesmenPendingSubscriptionPaymentService(Tradesmen tradesmen, EventBus<ApplicationEvent> eventBus,
                                                      TradesmanBuilder tradesmanBuilder) {
        this.tradesmen = Objects.requireNonNull(tradesmen);
        this.eventBus = eventBus;
        this.tradesmanBuilder = Objects.requireNonNull(tradesmanBuilder);
    }

    @Override
    public Void handle(PublishTradesmenPendingSubscriptionPayment command) {
        List<Tradesman> tradesmenPendingPayment = tradesmen.findAll();

        tradesmenPendingPayment.forEach(this::updateSubscriptionToPending);

        eventBus.publish(
                TradesmenSubscriptionPendingPayment.withTradesmen(
                        tradesmenPendingPayment.stream()
                                .map(tradesman -> MemberPayment.of(tradesman.entityId(), tradesman.paymentMethod()))
                                .toList()));
        return null;
    }

    public void updateSubscriptionToPending(Tradesman tradesman) {
        tradesmanBuilder.clear();
        tradesmanBuilder
                .withTradesman(tradesman)
                .withSubscriptionStatus(SubscriptionStatus.PENDING_PAYMENT);
        Tradesman updatedTradesman = tradesmanBuilder.build(tradesman.entityId());

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
    }

}
