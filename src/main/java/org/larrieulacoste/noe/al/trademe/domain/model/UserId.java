package org.larrieulacoste.noe.al.trademe.domain.model;

import java.util.Objects;
import java.util.UUID;

public final class UserId {
    private final String value;

    private UserId(String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static UserId fromUUID(UUID uuid) {
        return new UserId(Objects.requireNonNull(uuid).toString());
    }

    public static UserId of(String value) {
        return new UserId(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "UserId{" +
                "value='" + value + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserId)) return false;

        UserId userId = (UserId) o;

        return value.equals(userId.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
