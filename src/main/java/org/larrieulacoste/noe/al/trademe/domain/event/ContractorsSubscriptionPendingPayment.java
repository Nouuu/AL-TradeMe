package org.larrieulacoste.noe.al.trademe.domain.event;

import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.MemberPayment;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public record ContractorsSubscriptionPendingPayment(
        EventId eventId,
        ZonedDateTime occurredDate,
        List<MemberPayment> contractorsPayments
) implements ApplicationEvent {
    public ContractorsSubscriptionPendingPayment {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(occurredDate);
        Objects.requireNonNull(contractorsPayments);
    }

    public static ContractorsSubscriptionPendingPayment withContractors(List<MemberPayment> contractorsPayments) {
        return new ContractorsSubscriptionPendingPayment(EventId.create(), ZonedDateTime.now(), contractorsPayments);
    }

    @Override
    public List<MemberPayment> contractorsPayments() {
        return new ArrayList<>(contractorsPayments);
    }
}
