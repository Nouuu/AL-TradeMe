package org.larrieulacoste.noe.al.trademe.shared_kernel.model;

import java.util.Arrays;

public enum MemberType {
    TRADESMAN("Tradesman"),
    CONTRACTOR("Contractor");

    public final String value;

    MemberType(String value) {
        this.value = value;
    }


    public static MemberType fromString(String name) {
        return Arrays.stream(MemberType.values())
                .filter(memberType -> memberType.value.equals(name))
                .findFirst().orElse(null);
    }

    @Override
    public String toString() {
        return "MemberType{" +
                "value='" + value + '\'' +
                '}';
    }
}
