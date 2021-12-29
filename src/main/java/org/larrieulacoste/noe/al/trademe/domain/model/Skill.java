package org.larrieulacoste.noe.al.trademe.domain.model;

import org.larrieulacoste.noe.al.trademe.features.members.domain.NotEmptyString;

import java.util.Objects;

public final class Skill {
    private final NotEmptyString skillName;
    private final int requiredLevel;

    private Skill(NotEmptyString skillName, int requiredLevel) {
        this.skillName = Objects.requireNonNull(skillName);
        this.requiredLevel = requiredLevel;
    }

    public static Skill of(NotEmptyString skillName, int requiredLevel) {
        return new Skill(skillName, requiredLevel);
    }

    public NotEmptyString getSkillName() {
        return skillName;
    }

    public int getRequiredLevel() {
        return requiredLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Skill skill = (Skill) o;

        if (requiredLevel != skill.requiredLevel) return false;
        return skillName.equals(skill.skillName);
    }

    @Override
    public int hashCode() {
        int result = skillName.hashCode();
        result = 31 * result + requiredLevel;
        return result;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "skillName=" + skillName +
                ", requiredLevel=" + requiredLevel +
                '}';
    }
}
