package org.larrieulacoste.noe.al.trademe.features.projects.domain;

import org.larrieulacoste.noe.al.trademe.features.members.domain.NotEmptyString;
import org.larrieulacoste.noe.al.trademe.kernel.validators.DateValidators;
import org.larrieulacoste.noe.al.trademe.kernel.validators.StringValidators;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProjectBuilder {
    private final StringValidators stringValidator;
    private final DateValidators dateValidators;

    private NotEmptyString taskName;
    private List<Skill> requiredSkills;
    private List<Profession> professions;
    private EntityId contractorId;
    private List<EntityId> tradesmenIds = new ArrayList<>();
    private Period period;
    private DailyRate dailyRate;
    private Location location;

    public ProjectBuilder(StringValidators stringValidator, DateValidators dateValidators) {
        this.stringValidator = stringValidator;
        this.dateValidators = dateValidators;
    }

    public ProjectBuilder withTaskName(String taskName) {
        this.taskName = NotEmptyString.of(taskName, stringValidator);
        return this;
    }

    public ProjectBuilder withRequiredSkillsString(List<SkillRequest> requiredSkills) {
        this.requiredSkills = Objects.requireNonNull(requiredSkills).stream()
                .map(requiredSkill -> Skill.of(
                        NotEmptyString.of(requiredSkill.skillName(), stringValidator),
                        requiredSkill.requiredLevel()))
                .toList();
        return this;
    }

    public ProjectBuilder withRequiredSkills(List<Skill> requiredSkills) {
        this.requiredSkills = new ArrayList<>(requiredSkills);
        return this;
    }

    public ProjectBuilder withProfessionsString(List<String> professions) {
        this.professions = professions.stream().map(profession -> Profession.of(
                        NotEmptyString.of(profession, stringValidator)))
                .toList();
        return this;
    }

    public ProjectBuilder withProfessions(List<Profession> professions) {
        this.professions = new ArrayList<>(professions);
        return this;
    }

    public ProjectBuilder withContractorId(String contractorId) {
        this.contractorId = EntityId.of(contractorId);
        return this;
    }

    public ProjectBuilder withTradesmenIds(List<String> tradesmenIds) {
        this.tradesmenIds = tradesmenIds.stream().map(EntityId::of).toList();
        return this;
    }

    public ProjectBuilder withTradesmenEntityIds(List<EntityId> tradesmenIds) {
        this.tradesmenIds = new ArrayList<>(tradesmenIds);
        return this;
    }

    public ProjectBuilder addTradesmanId(EntityId tradesmanId) {
        if(!this.tradesmenIds.contains(tradesmanId)) {
            this.tradesmenIds.add(tradesmanId);
        }
        return this;
    }

    public ProjectBuilder withPeriod(ZonedDateTime startDate, ZonedDateTime endDate) {
        this.period = Period.of(
                startDate,
                endDate,
                dateValidators);
        return this;
    }

    public ProjectBuilder withDailyRate(double dailyRate) {
        this.dailyRate = DailyRate.of(
                Amount.of(dailyRate));
        return this;
    }

    public ProjectBuilder withLocation(String locationName, Double latitude, Double longitude) {
        this.location = Location.of(
                Coordinate.of(longitude, latitude),
                NotEmptyString.of(locationName, stringValidator));
        return this;
    }

    public Project build(EntityId id) {
        return Project.of(
                id,
                taskName,
                requiredSkills,
                professions,
                period,
                contractorId,
                tradesmenIds,
                dailyRate,
                location);
    }

    public void clear() {
        taskName = null;
        requiredSkills = null;
        professions = null;
        period = null;
        contractorId = null;
        tradesmenIds = new ArrayList<>();
        dailyRate = null;
        location = null;
    }

    public ProjectBuilder withProject(Project project) {
        taskName = project.taskName();
        requiredSkills = new ArrayList<>(project.requiredSkills());
        professions = new ArrayList<>(project.professions());
        period = project.period();
        contractorId = project.contractorId();
        tradesmenIds = new ArrayList<>(project.tradesmenIds());
        dailyRate = project.dailyRate();
        location = project.location();
        return this;
    }
}
