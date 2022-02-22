package org.larrieulacoste.noe.al.trademe.application.event;

import java.util.ArrayList;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.larrieulacoste.noe.al.trademe.domain.model.ActivityRadius;
import org.larrieulacoste.noe.al.trademe.domain.model.Amount;
import org.larrieulacoste.noe.al.trademe.domain.model.Coordinate;
import org.larrieulacoste.noe.al.trademe.domain.model.DailyRate;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.domain.model.Location;
import org.larrieulacoste.noe.al.trademe.domain.model.PaymentMethod;
import org.larrieulacoste.noe.al.trademe.domain.model.Profession;
import org.larrieulacoste.noe.al.trademe.domain.model.TradesmanProfessionalAbilites;
import org.larrieulacoste.noe.al.trademe.features.members.domain.NotEmptyString;
import org.larrieulacoste.noe.al.trademe.kernel.validators.SimpleStringValidators;
import org.larrieulacoste.noe.al.trademe.kernel.validators.StringValidators;

class TradesmanEventEntityTest {
    PaymentMethod paymentMethod = PaymentMethod.withPaypal("paypalemail");
    StringValidators stringValidators;
    TradesmanProfessionalAbilites abilites;
    Location location;

    @BeforeEach
    public void setUp() {
        stringValidators = new SimpleStringValidators();
        location = Location.of(Coordinate.of(0, 0), NotEmptyString.of("center of the world", stringValidators));
        abilites = TradesmanProfessionalAbilites.of(
                Profession.of(NotEmptyString.of("worker", stringValidators)),
                new ArrayList<>(),
                ActivityRadius.of(0),
                DailyRate.of(Amount.of(20)));

    }

    @Test
    void of() {
        EntityId entityId = EntityId.of("123");
        TradesmanEventEntity tradesmanEventEntity = TradesmanEventEntity.of(entityId, "firstname", "lastname", "email",
                "password", paymentMethod, location, abilites);
        Assertions.assertThat(tradesmanEventEntity.entityId()).isEqualTo(entityId);
        Assertions.assertThat(tradesmanEventEntity.firstname()).isEqualTo("firstname");
        Assertions.assertThat(tradesmanEventEntity.lastname()).isEqualTo("lastname");
        Assertions.assertThat(tradesmanEventEntity.email()).isEqualTo("email");
        Assertions.assertThat(tradesmanEventEntity.password()).isEqualTo("password");
        Assertions.assertThat(tradesmanEventEntity.paymentMethod()).isEqualTo(paymentMethod);

    }

    @Test
    void withoutPassword() {
        EntityId entityId = EntityId.of("123");
        TradesmanEventEntity tradesmanEventEntity = TradesmanEventEntity.withoutPassword(entityId, "firstname",
                "lastname", "email", paymentMethod, location, abilites);
        Assertions.assertThat(tradesmanEventEntity.entityId()).isEqualTo(entityId);
        Assertions.assertThat(tradesmanEventEntity.firstname()).isEqualTo("firstname");
        Assertions.assertThat(tradesmanEventEntity.lastname()).isEqualTo("lastname");
        Assertions.assertThat(tradesmanEventEntity.email()).isEqualTo("email");
        Assertions.assertThat(tradesmanEventEntity.paymentMethod()).isEqualTo(paymentMethod);
        Assertions.assertThat(tradesmanEventEntity.password()).isNull();
        Assertions.assertThat(tradesmanEventEntity.address().locationName().value()).isEqualTo("center of the world");
    }

    @Test
    void testAbilites() {
        EntityId entityId = EntityId.of("123");
        TradesmanEventEntity tradesmanEventEntity = TradesmanEventEntity.withoutPassword(entityId, "firstname",
                "lastname", "email", paymentMethod, location, abilites);
        TradesmanProfessionalAbilites abilites = tradesmanEventEntity.professionalAblilites();

        Assertions.assertThat(tradesmanEventEntity.address()).isEqualTo(location);
        Assertions.assertThat(abilites.activityRadius()).isEqualTo(ActivityRadius.of(0));
        Assertions.assertThat(abilites.skills()).isEmpty();
        Assertions.assertThat(abilites.profession())
                .isEqualTo(Profession.of(NotEmptyString.of("worker", stringValidators)));
        Assertions.assertThat(abilites.dailyRate()).isEqualTo(DailyRate.of(Amount.of(20)));
    }

    @Test
    void withEntityIdOnly() {
        EntityId entityId = EntityId.of("123");
        TradesmanEventEntity tradesmanEventEntity = TradesmanEventEntity.withEntityIdOnly(entityId);
        Assertions.assertThat(tradesmanEventEntity.entityId()).isEqualTo(entityId);
        Assertions.assertThat(tradesmanEventEntity.firstname()).isNull();
        Assertions.assertThat(tradesmanEventEntity.lastname()).isNull();
        Assertions.assertThat(tradesmanEventEntity.email()).isNull();
        Assertions.assertThat(tradesmanEventEntity.password()).isNull();
        Assertions.assertThat(tradesmanEventEntity.paymentMethod()).isNull();
    }

    @Test
    void withEntityIdAndPaymentMethodOnly() {
        EntityId entityId = EntityId.of("123");
        TradesmanEventEntity tradesmanEventEntity = TradesmanEventEntity.withEntityIdAndPaymentMethodOnly(entityId,
                paymentMethod);
        Assertions.assertThat(tradesmanEventEntity.entityId()).isEqualTo(entityId);
        Assertions.assertThat(tradesmanEventEntity.firstname()).isNull();
        Assertions.assertThat(tradesmanEventEntity.lastname()).isNull();
        Assertions.assertThat(tradesmanEventEntity.email()).isNull();
        Assertions.assertThat(tradesmanEventEntity.password()).isNull();
        Assertions.assertThat(tradesmanEventEntity.paymentMethod()).isEqualTo(paymentMethod);

    }
}