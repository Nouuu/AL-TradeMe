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
    private final TradesmanBuilder tradesmanBuilder;

    UpdateTradesmanService(Tradesmen tradesmen, MemberValidationService memberValidationService,
            EventBus<ApplicationEvent> eventBus, TradesmanBuilder tradesmanBuilder) {
        this.tradesmen = Objects.requireNonNull(tradesmen);
        this.memberValidationService = memberValidationService;
        this.eventBus = eventBus;
        this.tradesmanBuilder = tradesmanBuilder;
    }

    @Override
    public Tradesman handle(UpdateTradesman updateTradesman) {
        Tradesman inMemoryTradesman = tradesmen.byId(EntityId.of(updateTradesman.tradesmanId()));

        memberValidationService.validateUpdateTradesman(updateTradesman);

        tradesmanBuilder.clear();
        tradesmanBuilder.withTrademan(inMemoryTradesman);
        if (updateTradesman.lastname() != null) {
            tradesmanBuilder.withLastname(updateTradesman.lastname());
        }
        if (updateTradesman.firstname() != null) {
            tradesmanBuilder.withFirstname(updateTradesman.firstname());
        }
        if (updateTradesman.email() != null) {
            tradesmanBuilder.withEmail(updateTradesman.email());
        }
        if (updateTradesman.password() != null) {
            tradesmanBuilder.withPassword(updateTradesman.password());
        }

        Tradesman updatedTradesman = tradesmanBuilder.build(inMemoryTradesman.entityId());
        tradesmen.save(updatedTradesman);

        eventBus.publish(
                TradesmanUpdated.withTradesman(tradesmanBuilder.buildTradesmanEventEntityWithoutPassword()));
        return updatedTradesman;
    }
}
