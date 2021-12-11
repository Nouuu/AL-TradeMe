package org.larrieulacoste.noe.al.trademe.domain.model;

import java.util.Objects;

public class Coordinate {
    private final Double longitude; // Y
    private final Double latitude; // X

    private Coordinate(Double longitude, Double latitude) {
        this.longitude = Objects.requireNonNull(longitude);
        this.latitude = Objects.requireNonNull(latitude);
    }

    public static Coordinate of(Double longitude, Double latitude) {
        return new Coordinate(longitude, latitude);
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinate that = (Coordinate) o;

        if (!longitude.equals(that.longitude)) return false;
        return latitude.equals(that.latitude);
    }

    @Override
    public int hashCode() {
        int result = longitude.hashCode();
        result = 31 * result + latitude.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
