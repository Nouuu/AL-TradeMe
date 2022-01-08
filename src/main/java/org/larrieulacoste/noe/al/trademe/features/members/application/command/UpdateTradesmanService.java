package org.larrieulacoste.noe.al.trademe.features.members.application.command;

import org.larrieulacoste.noe.al.trademe.application.event.TradesmanEventEntity;
import org.larrieulacoste.noe.al.trademe.application.event.TradesmanUpdated;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.features.members.application.MemberValidationService;
import org.larrieulacoste.noe.al.trademe.features.members.domain.*;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;

import javax.enterprise.context.ApplicationScoped;
import java.util.Objects;

@ApplicationScoped
public class UpdateTradesmanService implements CommandHandler<UpdateTradesman, Tradesman> {
    private final Tradesmen tradesmen;
    private final MemberValidationService memberValidationService;
    private final EventBus<ApplicationEvent> eventBus;

    UpdateTradesmanService(Tradesmen tradesmen, MemberValidationService memberValidationService, EventBus<ApplicationEvent> eventBus) {
        this.tradesmen = Objects.requireNonNull(tradesmen);
        this.memberValidationService = memberValidationService;
        this.eventBus = eventBus;
    }

    @Override
    public Tradesman handle(UpdateTradesman updateTradesman) {
        Tradesman inMemoryTradesman = tradesmen.byId(EntityId.of(updateTradesman.tradesmanId));

        memberValidationService.validateUpdateTradesman(updateTradesman);

        Tradesman updatedTradesman = Tradesman.of(
                inMemoryTradesman.getEntityId(),
                updateTradesman.lastname != null ? NotEmptyString.of(updateTradesman.lastname) : inMemoryTradesman.getLastname(),
                updateTradesman.firstname != null ? NotEmptyString.of(updateTradesman.firstname) : inMemoryTradesman.getLastname(),
                updateTradesman.email != null ? EmailAddress.of(updateTradesman.email) : inMemoryTradesman.getEmail(),
                updateTradesman.password != null ? Password.of(updateTradesman.password) : inMemoryTradesman.getPassword(),
                inMemoryTradesman.getSubscriptionStatus(),
                inMemoryTradesman.getPaymentMethod());
        tradesmen.save(updatedTradesman);

        eventBus.publish(TradesmanUpdated.withTradesman(TradesmanEventEntity.withoutPassword(inMemoryTradesman.getEntityId(),
                updateTradesman.firstname, updateTradesman.lastname, updateTradesman.email, inMemoryTradesman.getPaymentMethod())));
        return updatedTradesman;
    }
}
