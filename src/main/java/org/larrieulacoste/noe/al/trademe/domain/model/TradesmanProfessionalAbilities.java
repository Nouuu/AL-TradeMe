package org.larrieulacoste.noe.al.trademe.domain.model;

import java.util.List;
import java.util.Objects;

public record TradesmanProfessionalAbilities(Profession profession,
    Location address,
    List<Skill> skills,
    ActivityPerimeter activityPerimeter,
    DailyRate dailyRate) {
  public TradesmanProfessionalAbilities {
    Objects.requireNonNull(profession);
    Objects.requireNonNull(address);
    Objects.requireNonNull(skills);
    Objects.requireNonNull(activityPerimeter);
    Objects.requireNonNull(dailyRate);
  }
}
