package org.larrieulacoste.noe.al.trademe.domain.model;

public record Amount(double value) {
    public static Amount of(double value) {
        return new Amount(value);
    }
}
