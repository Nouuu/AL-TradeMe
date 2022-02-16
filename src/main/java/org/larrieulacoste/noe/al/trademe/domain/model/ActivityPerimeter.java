package org.larrieulacoste.noe.al.trademe.domain.model;

import java.util.Objects;

public record ActivityPerimeter(double longitude,
    double latitude,
    double activityRadius) {
  public ActivityPerimeter {
    Objects.requireNonNull(longitude);
    Objects.requireNonNull(latitude);
    Objects.requireNonNull(activityRadius);
  }
}
