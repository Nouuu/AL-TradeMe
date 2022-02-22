package org.larrieulacoste.noe.al.trademe.domain.model;

import java.util.List;
import java.util.Objects;

public record TradesmanProfessionalAbilites(
    Profession profession,
    List<Skill> skills,
    ActivityRadius activityRadius,
    DailyRate dailyRate) {
  public TradesmanProfessionalAbilites {
    Objects.requireNonNull(profession);
    Objects.requireNonNull(skills);
    Objects.requireNonNull(activityRadius);
    Objects.requireNonNull(dailyRate);
  }

  public static TradesmanProfessionalAbilites of(Profession profession,
      List<Skill> skills,
      ActivityRadius activityRadius,
      DailyRate dailyRate) {
    return new TradesmanProfessionalAbilites(profession, skills, activityRadius, dailyRate);
  }
}
