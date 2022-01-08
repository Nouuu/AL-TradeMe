package org.larrieulacoste.noe.al.trademe.domain.model;

import java.util.Objects;
import java.util.UUID;

public final class EntityId {
    public final String value;

    private EntityId(String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static EntityId fromUUID(UUID uuid) {
        return new EntityId(Objects.requireNonNull(uuid).toString());
    }

    public static EntityId of(String value) {
        return new EntityId(value);
    }

    @Override
    public String toString() {
        return "EntityId{" +
                "value='" + value + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntityId)) return false;

        EntityId entityId = (EntityId) o;

        return value.equals(entityId.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
