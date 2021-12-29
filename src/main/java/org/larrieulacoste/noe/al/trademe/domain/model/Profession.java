package org.larrieulacoste.noe.al.trademe.domain.model;

import org.larrieulacoste.noe.al.trademe.features.members.domain.NotEmptyString;

import java.util.Objects;

public final class Profession {
    private final NotEmptyString professionName;

    private Profession(NotEmptyString professionName) {
        this.professionName = Objects.requireNonNull(professionName);
    }

    public static Profession of(NotEmptyString professionName) {
        return new Profession(professionName);
    }

    public NotEmptyString getProfessionName() {
        return professionName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Profession that = (Profession) o;

        return professionName.equals(that.professionName);
    }

    @Override
    public int hashCode() {
        return professionName.hashCode();
    }

    @Override
    public String toString() {
        return "Profession{" +
                "professionName=" + professionName +
                '}';
    }
}
