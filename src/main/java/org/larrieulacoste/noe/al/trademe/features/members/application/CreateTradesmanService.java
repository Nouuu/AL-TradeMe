package org.larrieulacoste.noe.al.trademe.features.members.application;

import org.larrieulacoste.noe.al.trademe.application.event.NewTradesmanRegistered;
import org.larrieulacoste.noe.al.trademe.application.event.TradesmanEventEntity;
import org.larrieulacoste.noe.al.trademe.application.exception.InvalidUserException;
import org.larrieulacoste.noe.al.trademe.domain.model.EmailAddress;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.domain.model.NotEmptyString;
import org.larrieulacoste.noe.al.trademe.domain.model.Password;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesman;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesmen;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CreateTradesmanService implements CommandHandler<CreateTradesman, EntityId> {
    private final Tradesmen tradesmen;
    private final MemberValidationService memberValidationService;
    private final EventBus<ApplicationEvent> eventBus;

    public CreateTradesmanService(Tradesmen tradesmen, MemberValidationService memberValidationService, EventBus<ApplicationEvent> eventBus) {
        this.tradesmen = tradesmen;
        this.memberValidationService = memberValidationService;
        this.eventBus = eventBus;
    }

    @Override
    public EntityId handle(CreateTradesman createTradesman) {
        if (!memberValidationService.isTradesmanValid(createTradesman)) {
            throw new InvalidUserException("Invalid tradesman creation");
        }

        final EntityId userId = tradesmen.nextId();
        Tradesman tradesman = Tradesman.of(
                userId,
                NotEmptyString.of(createTradesman.lastname),
                NotEmptyString.of(createTradesman.firstname),
                EmailAddress.of(createTradesman.email),
                Password.of(createTradesman.password)
        );
        tradesmen.save(tradesman);

        eventBus.publish(NewTradesmanRegistered.withTradesman(new TradesmanEventEntity(userId, createTradesman.firstname,
                createTradesman.lastname, createTradesman.email, createTradesman.password)));

        return userId;
    }
}