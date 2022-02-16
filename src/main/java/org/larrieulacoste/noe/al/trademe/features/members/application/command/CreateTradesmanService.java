package org.larrieulacoste.noe.al.trademe.features.members.application.command;

import org.larrieulacoste.noe.al.trademe.application.event.TradesmanEventEntity;
import org.larrieulacoste.noe.al.trademe.application.event.TradesmanRegistered;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.domain.model.PaymentMethod;
import org.larrieulacoste.noe.al.trademe.features.members.domain.*;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;
import org.larrieulacoste.noe.al.trademe.kernel.validators.StringValidators;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CreateTradesmanService implements CommandHandler<CreateTradesman, EntityId> {
    private final Tradesmen tradesmen;
    private final MemberValidationService memberValidationService;
    private final EventBus<ApplicationEvent> eventBus;
    private final StringValidators stringValidators;

    CreateTradesmanService(Tradesmen tradesmen, MemberValidationService memberValidationService, EventBus<ApplicationEvent> eventBus, StringValidators stringValidators) {
        this.tradesmen = tradesmen;
        this.memberValidationService = memberValidationService;
        this.eventBus = eventBus;
        this.stringValidators = stringValidators;
    }

    @Override
    public EntityId handle(CreateTradesman createTradesman) {
        memberValidationService.validateCreateTradesman(createTradesman);

        final EntityId userId = tradesmen.nextId();
        Tradesman tradesman = Tradesman.of(
                userId,
                NotEmptyString.of(createTradesman.lastname(), stringValidators),
                NotEmptyString.of(createTradesman.firstname(), stringValidators),
                EmailAddress.of(createTradesman.email(), stringValidators),
                Password.of(createTradesman.password(), stringValidators),
                SubscriptionStatus.PENDING_PAYMENT,
                PaymentMethod.of(createTradesman.paymentMethodType(), createTradesman.paymentMethodRessource())
        );
        tradesmen.save(tradesman);

        eventBus.publish(TradesmanRegistered.withTradesman(TradesmanEventEntity.of(
            userId, createTradesman.firstname(), createTradesman.lastname(), createTradesman.email(),
            createTradesman.password(), PaymentMethod.of(createTradesman.paymentMethodType(), createTradesman.paymentMethodRessource()),
            tradesman.professionalAbilities(), tradesman.projects()
        )));

        return userId;
    }
}