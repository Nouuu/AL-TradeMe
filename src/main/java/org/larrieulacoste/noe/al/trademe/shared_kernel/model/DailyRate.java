package org.larrieulacoste.noe.al.trademe.shared_kernel.model;

import java.util.Objects;

public record DailyRate(Amount amount) {

  public DailyRate {
    Objects.requireNonNull(amount);
  }

  public static DailyRate of(Amount amount) {
    return new DailyRate(amount);
  }
}
