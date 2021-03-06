package org.larrieulacoste.noe.al.trademe.features.members.application.command;

import org.larrieulacoste.noe.al.trademe.domain.event.TradesmanUpdated;
import org.larrieulacoste.noe.al.trademe.features.members.domain.MemberValidationService;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesman;
import org.larrieulacoste.noe.al.trademe.features.members.domain.TradesmanBuilder;
import org.larrieulacoste.noe.al.trademe.features.members.domain.Tradesmen;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandHandler;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.EntityId;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UpdateTradesmanAbilitiesService implements CommandHandler<UpdateTradesmanAbilities, Tradesman> {
  private final Tradesmen tradesmen;
  private final EventBus<ApplicationEvent> eventBus;
  private final TradesmanBuilder tradesmanBuilder;
  private final MemberValidationService memberValidationService;

  public UpdateTradesmanAbilitiesService(Tradesmen tradesmen, EventBus<ApplicationEvent> eventBus,
      TradesmanBuilder tradesmanBuilder, MemberValidationService memberValidationService) {
    this.tradesmen = tradesmen;
    this.eventBus = eventBus;
    this.tradesmanBuilder = tradesmanBuilder;
    this.memberValidationService = memberValidationService;
  }

  @Override
  public Tradesman handle(UpdateTradesmanAbilities updateAbilitiescommand) {
    memberValidationService.validateUpdateAbilities(updateAbilitiescommand);

    Tradesman inMemoryTradesman = tradesmen.byId(EntityId.of(updateAbilitiescommand.tradesmanId()));
    tradesmanBuilder.clear();
    tradesmanBuilder.withTradesman(inMemoryTradesman);

    if (updateAbilitiescommand.activityRadius() != null) {
      tradesmanBuilder.withActivityRadius(updateAbilitiescommand.activityRadius());
    }
    if (updateAbilitiescommand.dailyRate() != null) {
      tradesmanBuilder.withDailyRate(updateAbilitiescommand.dailyRate());
    }
    if (updateAbilitiescommand.profession() != null) {
      tradesmanBuilder.withProfession(updateAbilitiescommand.profession());
    }
    if (updateAbilitiescommand.skills() != null) {
      tradesmanBuilder.withSkillsRequest(updateAbilitiescommand.skills());
    }
    if (updateAbilitiescommand.unavailabilityPeriods() != null) {
      tradesmanBuilder.withUnavailabilityPeriods(updateAbilitiescommand.unavailabilityPeriods());
    }

    Tradesman updatedTradesman = tradesmanBuilder.build(inMemoryTradesman.entityId());
    tradesmen.save(updatedTradesman);

    eventBus.publish(TradesmanUpdated.of(
        updatedTradesman.entityId(),
        updatedTradesman.firstname().value(),
        updatedTradesman.lastname().value(),
        updatedTradesman.email().value(),
        updatedTradesman.paymentMethod(),
        updatedTradesman.address(),
        updatedTradesman.professionalAbilities()));
    return updatedTradesman;
  }

}
