package org.larrieulacoste.noe.al.trademe.application.event;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;

import static org.junit.jupiter.api.Assertions.*;

class TradesmanEventEntityTest {

    @Test
    void of() {
        EntityId entityId = EntityId.of("123");
        TradesmanEventEntity tradesmanEventEntity =
                TradesmanEventEntity.of(entityId, "firstname", "lastname", "email", "password");
        Assertions.assertThat(tradesmanEventEntity.entityId).isEqualTo(entityId);
        Assertions.assertThat(tradesmanEventEntity.firstname).isEqualTo("firstname");
        Assertions.assertThat(tradesmanEventEntity.lastname).isEqualTo("lastname");
        Assertions.assertThat(tradesmanEventEntity.email).isEqualTo("email");
        Assertions.assertThat(tradesmanEventEntity.password).isEqualTo("password");
    }

    @Test
    void withoutPassword() {
        EntityId entityId = EntityId.of("123");
        TradesmanEventEntity tradesmanEventEntity =
                TradesmanEventEntity.withoutPassword(entityId, "firstname", "lastname", "email");
        Assertions.assertThat(tradesmanEventEntity.entityId).isEqualTo(entityId);
        Assertions.assertThat(tradesmanEventEntity.firstname).isEqualTo("firstname");
        Assertions.assertThat(tradesmanEventEntity.lastname).isEqualTo("lastname");
        Assertions.assertThat(tradesmanEventEntity.email).isEqualTo("email");
        Assertions.assertThat(tradesmanEventEntity.password).isNull();
    }

    @Test
    void withEntityIdOnly() {
        EntityId entityId = EntityId.of("123");
        TradesmanEventEntity tradesmanEventEntity =
                TradesmanEventEntity.withEntityIdOnly(entityId);
        Assertions.assertThat(tradesmanEventEntity.entityId).isEqualTo(entityId);
        Assertions.assertThat(tradesmanEventEntity.firstname).isNull();
        Assertions.assertThat(tradesmanEventEntity.lastname).isNull();
        Assertions.assertThat(tradesmanEventEntity.email).isNull();
        Assertions.assertThat(tradesmanEventEntity.password).isNull();
    }
}