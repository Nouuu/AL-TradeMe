package org.larrieulacoste.noe.al.trademe.domain.model;

import java.util.Objects;

public record DailyRate(Amount amount) {

  public DailyRate {
    Objects.requireNonNull(amount);
  }

  public static DailyRate of(Amount amount) {
    return new DailyRate(amount);
  }
}
