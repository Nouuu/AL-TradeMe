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

    CreateTradesmanService(Tradesmen tradesmen, MemberValidationService memberValidationService,
            EventBus<ApplicationEvent> eventBus, StringValidators stringValidators) {
        this.tradesmen = tradesmen;
        this.memberValidationService = memberValidationService;
        this.eventBus = eventBus;
        this.stringValidators = stringValidators;
    }

    @Override
    public EntityId handle(CreateTradesman createTradesman) {
        memberValidationService.validateCreateTradesman(createTradesman);

        final EntityId userId = tradesmen.nextId();
        TradesmanBuilder tradesmanBuilder = new TradesmanBuilder(stringValidators);// TODO inject
        tradesmanBuilder
                .withLastname(createTradesman.lastname())
                .withFirstname(createTradesman.firstname())
                .withEmail(createTradesman.email())
                .withPassword(createTradesman.password())
                .withSubscribtionStatus(SubscriptionStatus.PENDING_PAYMENT)
                .withPaymentMethod(createTradesman.paymentMethodType(), createTradesman.paymentMethodRessource())
                .withLocationName(createTradesman.locationName());
        Tradesman tradesman = tradesmanBuilder.build(userId);
        tradesmen.save(tradesman);

        eventBus.publish(TradesmanRegistered.withTradesman(tradesmanBuilder.buildTradesmanEventEntity()));

        return userId;
    }
}