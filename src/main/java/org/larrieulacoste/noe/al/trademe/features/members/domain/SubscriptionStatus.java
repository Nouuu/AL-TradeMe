package org.larrieulacoste.noe.al.trademe.features.members.domain;

public enum SubscriptionStatus {
    PENDING_PAYMENT("PENDING_PAYMENT"),
    ACTIVE("ACTIVE"),
    PAYMENT_FAILED("PAYMENT_FAILED");

    public final String value;

    SubscriptionStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SubscriptionStatus{" +
                "value='" + value + '\'' +
                '}';
    }
}
