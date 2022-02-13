package org.larrieulacoste.noe.al.trademe.domain.model;

import org.larrieulacoste.noe.al.trademe.features.members.domain.NotEmptyString;

import java.util.Objects;

public record Location(
        Coordinate coordinate,
        NotEmptyString locationName
) {
    public Location {
        Objects.requireNonNull(coordinate);
        Objects.requireNonNull(locationName);
    }

    public static Location of(Coordinate coordinate, NotEmptyString locationName) {
        return new Location(coordinate, locationName);
    }
}
