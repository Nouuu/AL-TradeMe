package org.larrieulacoste.noe.al.trademe.domain.model;

import org.larrieulacoste.noe.al.trademe.features.members.domain.NotEmptyString;

import java.util.List;
import java.util.Objects;

public final class Project {
    private final NotEmptyString taskName;
    private final List<Skill> requiredSkills;
    private final List<Profession> professions;
    private final Period period;
    private final Double dailyRate;
    private final Location location;

    private Project(NotEmptyString taskName, List<Skill> requiredSkills, List<Profession> professions,
                    Period period, Double dailyRate, Location location) {
        this.taskName = Objects.requireNonNull(taskName);
        this.requiredSkills = Objects.requireNonNull(requiredSkills);
        this.professions = Objects.requireNonNull(professions);
        this.period = Objects.requireNonNull(period);
        this.dailyRate = Objects.requireNonNull(dailyRate);
        this.location = Objects.requireNonNull(location);
    }

    public static Project of(NotEmptyString taskName, List<Skill> requiredSkills, List<Profession> professions,
                             Period period, Double dailyRate, Location location) {
        return new Project(taskName, requiredSkills, professions, period, dailyRate, location);
    }

    public List<Skill> getRequiredSkills() {
        return List.copyOf(requiredSkills);
    }

    public List<Profession> getProfessions() {
        return List.copyOf(professions);
    }

    public NotEmptyString getTaskName() {
        return taskName;
    }

    public Period getPeriod() {
        return period;
    }

    public Double getDailyRate() {
        return dailyRate;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        if (!taskName.equals(project.taskName)) return false;
        if (!requiredSkills.equals(project.requiredSkills)) return false;
        if (!professions.equals(project.professions)) return false;
        if (!period.equals(project.period)) return false;
        if (!dailyRate.equals(project.dailyRate)) return false;
        return location.equals(project.location);
    }

    @Override
    public int hashCode() {
        int result = taskName.hashCode();
        result = 31 * result + requiredSkills.hashCode();
        result = 31 * result + professions.hashCode();
        result = 31 * result + period.hashCode();
        result = 31 * result + dailyRate.hashCode();
        result = 31 * result + location.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Project{" +
                "taskName=" + taskName +
                ", requiredSkills=" + requiredSkills +
                ", professions=" + professions +
                ", period=" + period +
                ", dailyRate=" + dailyRate +
                ", location=" + location +
                '}';
    }
}
