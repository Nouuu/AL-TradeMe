package org.larrieulacoste.noe.al.trademe.features.projects.domain;

import org.larrieulacoste.noe.al.trademe.domain.model.*;
import org.larrieulacoste.noe.al.trademe.features.members.domain.NotEmptyString;

import java.util.List;
import java.util.Objects;

public record Project(
        EntityId projectId,
        NotEmptyString taskName,
        List<Skill> requiredSkills,
        List<Profession> professions,
        EntityId contractorId,
        List<EntityId> tradesmenIds,
        Period period,
        DailyRate dailyRate,
        Location location) {

    public Project {
        Objects.requireNonNull(taskName);
        Objects.requireNonNull(requiredSkills);
        Objects.requireNonNull(professions);
        Objects.requireNonNull(contractorId);
        Objects.requireNonNull(tradesmenIds);
        Objects.requireNonNull(period);
        Objects.requireNonNull(location);
    }

    public static Project of(
            EntityId projectId,
            NotEmptyString taskName,
            List<Skill> requiredSkills,
            List<Profession> professions,
            Period period,
            EntityId contractorId,
            List<EntityId> tradesmenIds,
            DailyRate dailyRate,
            Location location) {
        return new Project(
                projectId,
                taskName,
                requiredSkills,
                professions,
                contractorId,
                tradesmenIds,
                period,
                dailyRate,
                location);
    }
}
