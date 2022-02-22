package org.larrieulacoste.noe.al.trademe.features.projects.domain;

import org.larrieulacoste.noe.al.trademe.domain.model.*;
import org.larrieulacoste.noe.al.trademe.features.members.domain.NotEmptyString;

import java.util.List;
import java.util.Objects;

public record Project(
        NotEmptyString taskName,
        List<Skill> requiredSkills,
        List<Profession> professions,
        Period period, DailyRate dailyRate,
        Location location) {

    public Project {
        Objects.requireNonNull(taskName);
        Objects.requireNonNull(requiredSkills);
        Objects.requireNonNull(professions);
        Objects.requireNonNull(period);
        Objects.requireNonNull(location);
    }

    public static Project of(NotEmptyString taskName, List<Skill> requiredSkills, List<Profession> professions,
            Period period, DailyRate dailyRate, Location location) {
        return new Project(taskName, requiredSkills, professions, period, dailyRate, location);
    }
}
