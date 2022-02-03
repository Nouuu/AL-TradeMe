package org.larrieulacoste.noe.al.trademe.features.members.application.command;

import org.larrieulacoste.noe.al.trademe.application.event.TradesmanEventEntity;
import org.larrieulacoste.noe.al.trademe.application.event.TradesmanRegistered;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.domain.model.PaymentMethod;
import org.larrieulacoste.noe.al.trademe.features.members.domain.*;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CreateTradesmanService implements CommandHandler<CreateTradesman, EntityId> {
    private final Tradesmen tradesmen;
    private final MemberValidationService memberValidationService;
    private final EventBus<ApplicationEvent> eventBus;

    CreateTradesmanService(Tradesmen tradesmen, MemberValidationService memberValidationService, EventBus<ApplicationEvent> eventBus) {
        this.tradesmen = tradesmen;
        this.memberValidationService = memberValidationService;
        this.eventBus = eventBus;
    }

    @Override
    public EntityId handle(CreateTradesman createTradesman) {
        memberValidationService.validateCreateTradesman(createTradesman);

        final EntityId userId = tradesmen.nextId();
        Tradesman tradesman = Tradesman.of(
                userId,
                NotEmptyString.of(createTradesman.lastname),
                NotEmptyString.of(createTradesman.firstname),
                EmailAddress.of(createTradesman.email),
                Password.of(createTradesman.password),
                SubscriptionStatus.PENDING_PAYMENT,
                PaymentMethod.of(createTradesman.paymentMethodType, createTradesman.paymentMethodRessource)

        );
        tradesmen.save(tradesman);

        eventBus.publish(TradesmanRegistered.withTradesman(TradesmanEventEntity.of(userId, createTradesman.firstname,
                createTradesman.lastname, createTradesman.email, createTradesman.password, PaymentMethod.of(createTradesman.paymentMethodType, createTradesman.paymentMethodRessource))));

        return userId;
    }
}