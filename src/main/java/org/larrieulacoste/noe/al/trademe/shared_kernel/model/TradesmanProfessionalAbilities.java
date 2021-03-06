package org.larrieulacoste.noe.al.trademe.shared_kernel.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public record TradesmanProfessionalAbilities(
    Profession profession,
    List<Skill> skills,
    ActivityRadius activityRadius,
    DailyRate dailyRate,
    List<Period> unavailability) {
  public TradesmanProfessionalAbilities {
    Objects.requireNonNull(profession);
    Objects.requireNonNull(skills);
    Objects.requireNonNull(activityRadius);
    Objects.requireNonNull(dailyRate);
    Objects.requireNonNull(unavailability);
  }

  public static TradesmanProfessionalAbilities of(Profession profession,
      List<Skill> skills,
      ActivityRadius activityRadius,
      DailyRate dailyRate,
      List<Period> unavailability) {
    return new TradesmanProfessionalAbilities(profession, skills, activityRadius, dailyRate, unavailability);
  }

    @Override
    public List<Skill> skills() {
        return new ArrayList<>(skills);
    }

    @Override
    public List<Period> unavailability() {
        return new ArrayList<>(unavailability);
    }
}
