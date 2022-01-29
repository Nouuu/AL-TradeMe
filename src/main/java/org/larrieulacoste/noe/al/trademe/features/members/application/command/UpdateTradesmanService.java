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
        private final Tradesmans tradesmans;
        private final MemberValidationService memberValidationService;
        private final EventBus<ApplicationEvent> eventBus;

        UpdateTradesmanService(Tradesmans tradesmans, MemberValidationService memberValidationService,
                        EventBus<ApplicationEvent> eventBus) {
                this.tradesmans = Objects.requireNonNull(tradesmans);
                this.memberValidationService = memberValidationService;
                this.eventBus = eventBus;
        }

        @Override
        public Tradesman handle(UpdateTradesman updateTradesman) {
                Tradesman inMemoryTradesman = tradesmans.byId(EntityId.of(updateTradesman.tradesmanId));

                memberValidationService.validateUpdateTradesman(updateTradesman);

                Tradesman updatedTradesman = Tradesman.of(
                                inMemoryTradesman.entityId,
                                updateTradesman.lastname != null ? NotEmptyString.of(updateTradesman.lastname)
                                                : inMemoryTradesman.lastname,
                                updateTradesman.firstname != null ? NotEmptyString.of(updateTradesman.firstname)
                                                : inMemoryTradesman.lastname,
                                updateTradesman.email != null ? EmailAddress.of(updateTradesman.email)
                                                : inMemoryTradesman.email,
                                updateTradesman.password != null ? Password.of(updateTradesman.password)
                                                : inMemoryTradesman.password,
                                inMemoryTradesman.subscriptionStatus,
                                inMemoryTradesman.paymentMethod);
                tradesmans.save(updatedTradesman);

                eventBus.publish(TradesmanUpdated.withTradesman(TradesmanEventEntity.withoutPassword(
                                inMemoryTradesman.entityId,
                                updateTradesman.firstname, updateTradesman.lastname, updateTradesman.email,
                                inMemoryTradesman.paymentMethod)));
                return updatedTradesman;
        }
}
