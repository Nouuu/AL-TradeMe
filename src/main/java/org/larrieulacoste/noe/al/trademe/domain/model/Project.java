package org.larrieulacoste.noe.al.trademe.domain.model;

import org.larrieulacoste.noe.al.trademe.features.members.domain.NotEmptyString;

import java.util.List;
import java.util.Objects;

public final class Project {
    public final NotEmptyString taskName;
    public final List<Skill> requiredSkills;
    public final List<Profession> professions;
    public final Period period;
    public final double dailyRate;
    public final Location location;

    private Project(NotEmptyString taskName, List<Skill> requiredSkills, List<Profession> professions,
                    Period period, double dailyRate, Location location) {
        this.taskName = Objects.requireNonNull(taskName);
        this.requiredSkills = Objects.requireNonNull(requiredSkills);
        this.professions = Objects.requireNonNull(professions);
        this.period = Objects.requireNonNull(period);
        this.dailyRate = dailyRate;
        this.location = Objects.requireNonNull(location);
    }

    public static Project of(NotEmptyString taskName, List<Skill> requiredSkills, List<Profession> professions,
                             Period period, double dailyRate, Location location) {
        return new Project(taskName, requiredSkills, professions, period, dailyRate, location);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        if (Double.compare(project.dailyRate, dailyRate) != 0) return false;
        if (!taskName.equals(project.taskName)) return false;
        if (!requiredSkills.equals(project.requiredSkills)) return false;
        if (!professions.equals(project.professions)) return false;
        if (!period.equals(project.period)) return false;
        return location.equals(project.location);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = taskName.hashCode();
        result = 31 * result + requiredSkills.hashCode();
        result = 31 * result + professions.hashCode();
        result = 31 * result + period.hashCode();
        temp = Double.doubleToLongBits(dailyRate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
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
