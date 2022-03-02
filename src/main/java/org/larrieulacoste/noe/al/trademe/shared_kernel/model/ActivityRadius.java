package org.larrieulacoste.noe.al.trademe.shared_kernel.model;

import java.util.Objects;

public record ActivityRadius(double activityRadius) {
  public ActivityRadius {
    Objects.requireNonNull(activityRadius);
  }

  public static ActivityRadius of(double activityRadius) {
    return new ActivityRadius(activityRadius);
  }
}
