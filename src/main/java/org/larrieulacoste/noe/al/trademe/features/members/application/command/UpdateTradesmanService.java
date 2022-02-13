package org.larrieulacoste.noe.al.trademe.features.members.application.command;

import org.larrieulacoste.noe.al.trademe.application.event.TradesmanEventEntity;
import org.larrieulacoste.noe.al.trademe.application.event.TradesmanUpdated;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.features.members.domain.*;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;
import org.larrieulacoste.noe.al.trademe.kernel.validators.StringValidators;

import javax.enterprise.context.ApplicationScoped;
import java.util.Objects;

@ApplicationScoped
public class UpdateTradesmanService implements CommandHandler<UpdateTradesman, Tradesman> {
    private final Tradesmen tradesmen;
    private final MemberValidationService memberValidationService;
    private final EventBus<ApplicationEvent> eventBus;
    private final StringValidators stringValidators;

    UpdateTradesmanService(Tradesmen tradesmen, MemberValidationService memberValidationService, EventBus<ApplicationEvent> eventBus, StringValidators stringValidators) {
        this.tradesmen = Objects.requireNonNull(tradesmen);
        this.memberValidationService = memberValidationService;
        this.eventBus = eventBus;
        this.stringValidators = stringValidators;
    }

    @Override
    public Tradesman handle(UpdateTradesman updateTradesman) {
        Tradesman inMemoryTradesman = tradesmen.byId(EntityId.of(updateTradesman.tradesmanId()));

        memberValidationService.validateUpdateTradesman(updateTradesman);

        Tradesman updatedTradesman = Tradesman.of(
                inMemoryTradesman.entityId(),
                updateTradesman.lastname() != null ? NotEmptyString.of(updateTradesman.lastname(), stringValidators) : inMemoryTradesman.lastname(),
                updateTradesman.firstname() != null ? NotEmptyString.of(updateTradesman.firstname(), stringValidators) : inMemoryTradesman.lastname(),
                updateTradesman.email() != null ? EmailAddress.of(updateTradesman.email(), stringValidators) : inMemoryTradesman.email(),
                updateTradesman.password() != null ? Password.of(updateTradesman.password(), stringValidators) : inMemoryTradesman.password(),
                inMemoryTradesman.subscriptionStatus(),
                inMemoryTradesman.paymentMethod());
        tradesmen.save(updatedTradesman);

        eventBus.publish(TradesmanUpdated.withTradesman(TradesmanEventEntity.withoutPassword(inMemoryTradesman.entityId(),
                updateTradesman.firstname(), updateTradesman.lastname(), updateTradesman.email(), inMemoryTradesman.paymentMethod())));
        return updatedTradesman;
    }
}
