package org.larrieulacoste.noe.al.trademe.domain.model;

import java.util.Arrays;

public enum MemberType {
    TRADESMAN("Tradesman"),
    CONTRACTOR("Contractor");

    private final String name;

    MemberType(String name) {
        this.name = name;
    }


    public static MemberType fromString(String name) {
        return Arrays.stream(MemberType.values())
                .filter(memberType -> memberType.name.equals(name))
                .findFirst().orElse(null);
    }

    @Override
    public String toString() {
        return name;
    }
}
