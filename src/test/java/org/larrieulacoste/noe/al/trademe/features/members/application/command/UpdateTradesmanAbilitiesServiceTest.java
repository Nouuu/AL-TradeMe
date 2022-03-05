package org.larrieulacoste.noe.al.trademe.features.members.application.command;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.larrieulacoste.noe.al.trademe.features.members.domain.*;
import org.larrieulacoste.noe.al.trademe.features.members.infrastructure.InMemoryTradesmen;
import org.larrieulacoste.noe.al.trademe.kernel.event.DefaultEventBus;
import org.larrieulacoste.noe.al.trademe.kernel.exception.InvalidUserException;
import org.larrieulacoste.noe.al.trademe.kernel.exception.UserNotFoundException;
import org.larrieulacoste.noe.al.trademe.kernel.logger.DefaultLoggerFactory;
import org.larrieulacoste.noe.al.trademe.kernel.logger.Logger;
import org.larrieulacoste.noe.al.trademe.kernel.validators.SimpleDateValidators;
import org.larrieulacoste.noe.al.trademe.kernel.validators.SimplePaymentInformationsValidator;
import org.larrieulacoste.noe.al.trademe.kernel.validators.SimpleStringValidators;
import org.larrieulacoste.noe.al.trademe.kernel.validators.StringValidators;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.*;

import java.util.ArrayList;
import java.util.HashMap;

class UpdateTradesmanAbilitiesServiceTest {

  UpdateTradesmanAbilitiesService updateTradesmanAbilitiesService;
  StringValidators stringValidators = new SimpleStringValidators();
  Logger logger = new DefaultLoggerFactory().getLogger(UpdateTradesmanAbilitiesServiceTest.class);
  Tradesmen tradesmanRepository = new InMemoryTradesmen(logger);

  @BeforeEach
  void setUp() {
    updateTradesmanAbilitiesService = new UpdateTradesmanAbilitiesService(tradesmanRepository,
        new DefaultEventBus<>(new HashMap<>(), logger),
        new TradesmanBuilder(stringValidators, new SimpleDateValidators()),
        new MemberValidationService(logger, stringValidators, new SimplePaymentInformationsValidator()));
  }

  @Test
  void callServiceOnUnknownTradesman() {
    var command = new UpdateTradesmanAbilities("id1", "charpentier", new ArrayList<>(), 10.0, 1.0, new ArrayList<>());
    Assertions.assertThatThrownBy(() -> {
      updateTradesmanAbilitiesService
          .handle(command);
    }).isInstanceOf(UserNotFoundException.class);
  }

  @Test
  void callServiceWithKnownTradesman() {
    Tradesman tradesman1 = Tradesman.of(EntityId.of("id1"),
        NotEmptyString.of("lastname", stringValidators),
        NotEmptyString.of("firstname", stringValidators),
        EmailAddress.of("email@email.fr", stringValidators),
        Password.of("passwordDesP0mm3s", stringValidators),
        Location.of(Coordinate.of(0.0, 0.0), NotEmptyString.of("locationName", stringValidators)),
        SubscriptionStatus.ACTIVE,
        PaymentMethod.of("BANK_ACCOUNT", "payment info"),
        TradesmanProfessionalAbilities.of(
            Profession.of(NotEmptyString.of("Profession", stringValidators)),
            new ArrayList<>(), ActivityRadius.of(1.0), DailyRate.of(Amount.of(420)), new ArrayList<>()));

    tradesmanRepository.save(tradesman1);
    Tradesman updatedTradesman = updateTradesmanAbilitiesService
        .handle(new UpdateTradesmanAbilities("id1", "Charpentier", new ArrayList<>(), 1.0, 1.0, new ArrayList<>()));
    Assertions.assertThat(updatedTradesman.professionalAbilities().profession())
        .isEqualTo(Profession.of(NotEmptyString.of("Charpentier", stringValidators)));
  }
}
