package org.larrieulacoste.noe.al.trademe.features.members.application.command;

import org.larrieulacoste.noe.al.trademe.domain.event.TradesmanRegistered;
import org.larrieulacoste.noe.al.trademe.features.members.domain.*;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.EntityId;

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
                .withSubscriptionStatus(SubscriptionStatus.PENDING_PAYMENT)
                .withPaymentMethod(createTradesman.paymentMethodType(), createTradesman.paymentMethodRessource())
                .withLocationName(createTradesman.locationName())
                .withDailyRate(createTradesman.dailyRate())
                .withLatitude(createTradesman.latitude())
                .withLongitude(createTradesman.longitude())
                .withActivityRadius(createTradesman.activityRadius())
                .withProfession(createTradesman.profession());
        Tradesman tradesman = tradesmanBuilder.build(userId);
        tradesmen.save(tradesman);

        eventBus.publish(TradesmanRegistered.of(
                userId,
                tradesman.firstname().value(),
                tradesman.lastname().value(),
                tradesman.email().value(),
                tradesman.paymentMethod(),
                tradesman.address(),
                tradesman.professionalAbilities()));

        return userId;
    }
}