package org.larrieulacoste.noe.al.trademe.shared_kernel.model;

import java.util.Objects;

public record MemberPayment(
        EntityId memberId,
        PaymentMethod paymentMethod
) {
    public MemberPayment {
        Objects.requireNonNull(memberId);
        Objects.requireNonNull(paymentMethod);
    }

    public static MemberPayment of(EntityId memberId,
                                   PaymentMethod paymentMethod) {
        return new MemberPayment(memberId, paymentMethod);
    }
}
