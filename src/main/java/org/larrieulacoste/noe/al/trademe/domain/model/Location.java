package org.larrieulacoste.noe.al.trademe.domain.model;

import org.larrieulacoste.noe.al.trademe.features.members.domain.NotEmptyString;

import java.util.Objects;

public record Location(
        Coordinate coordinate,
        NotEmptyString locationName
) {
    private static final double EARTH_RADIUS = 6371;
    public Location {
        Objects.requireNonNull(coordinate);
        Objects.requireNonNull(locationName);
    }

    public static Location of(Coordinate coordinate, NotEmptyString locationName) {
        return new Location(coordinate, locationName);
    }

    public double distance(Location location) {
        // Source: https://www.geeksforgeeks.org/program-distance-two-points-earth/
        double lon1 = Math.toRadians(coordinate().longitude());
        double lon2 = Math.toRadians(location.coordinate().longitude());
        double lat1 = Math.toRadians(coordinate().latitude());
        double lat2 = Math.toRadians(location.coordinate().latitude());
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2), 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return Math.abs(c * EARTH_RADIUS);
    }
}
