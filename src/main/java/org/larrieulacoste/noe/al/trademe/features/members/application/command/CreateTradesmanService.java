package org.larrieulacoste.noe.al.trademe.features.members.application.command;

import org.larrieulacoste.noe.al.trademe.application.event.TradesmanRegistered;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
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
    private final TradesmanBuilder tradesmanBuilder;

    CreateTradesmanService(Tradesmen tradesmen, MemberValidationService memberValidationService,
            EventBus<ApplicationEvent> eventBus, TradesmanBuilder tradesmanBuilder) {
        this.tradesmen = tradesmen;
        this.memberValidationService = memberValidationService;
        this.eventBus = eventBus;
        this.tradesmanBuilder = tradesmanBuilder;
    }

    @Override
    public EntityId handle(CreateTradesman createTradesman) {
        memberValidationService.validateCreateTradesman(createTradesman);

        final EntityId userId = tradesmen.nextId();
        tradesmanBuilder.clear();
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

        eventBus.publish(
                TradesmanRegistered.withTradesman(tradesmanBuilder.buildTradesmanEventEntityWithoutPassword()));

        return userId;
    }
}