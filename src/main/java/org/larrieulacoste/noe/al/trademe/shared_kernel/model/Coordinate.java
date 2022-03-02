package org.larrieulacoste.noe.al.trademe.shared_kernel.model;

public record Coordinate(
        double longitude, // Y
        double latitude // X
) {

    public static Coordinate of(double longitude, double latitude) {
        return new Coordinate(longitude, latitude);
    }
}
