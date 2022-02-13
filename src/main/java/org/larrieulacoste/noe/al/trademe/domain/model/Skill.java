package org.larrieulacoste.noe.al.trademe.domain.model;

import org.larrieulacoste.noe.al.trademe.features.members.domain.NotEmptyString;

import java.util.Objects;

public record Skill(
        NotEmptyString skillName,
        int requiredLevel
) {

    public Skill {
        Objects.requireNonNull(skillName);
    }

    public static Skill of(NotEmptyString skillName, int requiredLevel) {
        return new Skill(skillName, requiredLevel);
    }

}
