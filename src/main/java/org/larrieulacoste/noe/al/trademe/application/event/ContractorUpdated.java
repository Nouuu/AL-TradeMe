package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.domain.model.PaymentMethod;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.Objects;

public record ContractorUpdated(
        EventId eventId,
        ZonedDateTime occurredDate,
        EntityId contractorId,
        String firstname,
        String lastname,
        String email,
        PaymentMethod paymentMethod
) implements ApplicationEvent {

    public ContractorUpdated {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(occurredDate);
        Objects.requireNonNull(contractorId);
        Objects.requireNonNull(firstname);
        Objects.requireNonNull(lastname);
        Objects.requireNonNull(email);
        Objects.requireNonNull(paymentMethod);
    }

    public static ContractorUpdated of(
            EntityId contractorId,
            String firstname,
            String lastname,
            String email,
            PaymentMethod paymentMethod
    ) {
        return new ContractorUpdated(
                EventId.create(),
                ZonedDateTime.now(),
                contractorId,
                firstname,
                lastname,
                email,
                paymentMethod
        );
    }
}
