package org.larrieulacoste.noe.al.trademe.domain.model;

import java.util.Objects;

public final class Location {
    private final Coordinate coordinate;
    private final NotEmptyString locationName;

    private Location(Coordinate coordinate, NotEmptyString locationName) {
        this.coordinate = Objects.requireNonNull(coordinate);
        this.locationName = Objects.requireNonNull(locationName);
    }

    public static Location of(Coordinate coordinate, NotEmptyString locationName) {
        return new Location(coordinate, locationName);
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public NotEmptyString getLocationName() {
        return locationName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (!coordinate.equals(location.coordinate)) return false;
        return locationName.equals(location.locationName);
    }

    @Override
    public int hashCode() {
        int result = coordinate.hashCode();
        result = 31 * result + locationName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Location{" +
                "coordinate=" + coordinate +
                ", locationName=" + locationName +
                '}';
    }
}
