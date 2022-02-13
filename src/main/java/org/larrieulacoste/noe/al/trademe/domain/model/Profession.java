package org.larrieulacoste.noe.al.trademe.domain.model;

import org.larrieulacoste.noe.al.trademe.features.members.domain.NotEmptyString;

import java.util.Objects;

public record Profession(NotEmptyString professionName) {

    public Profession {
        Objects.requireNonNull(professionName);
    }

    public static Profession of(NotEmptyString professionName) {
        return new Profession(professionName);
    }

}
